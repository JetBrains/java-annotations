import jetbrains.sign.GpgSignSignatoryProvider
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import plugins.publishing.*

/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

buildscript {
    repositories {
        maven { url = uri("https://packages.jetbrains.team/maven/p/jcs/maven") }
    }
    dependencies {
        classpath("com.jetbrains:jet-sign:38")
    }
}

repositories {
    mavenCentral()
}

plugins {
    kotlin("multiplatform") version "1.9.22"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    `maven-publish`
    signing
    java
}

var projectVersion = project.findProperty("projectVersion") as String
val publishingUser: String? = System.getenv("PUBLISHING_USER")
val publishingPassword: String? = System.getenv("PUBLISHING_PASSWORD")
if (publishingPassword == null) {
    projectVersion += "-SNAPSHOT"
}
println("##teamcity[setParameter name='java.annotations.version' value='$projectVersion']")

// https://github.com/gradle/gradle/issues/847
group = "org.jetbrains.proto"
version = projectVersion

kotlin {
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    js(IR) {
        browser {}
    }

    jvmToolchain(8)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
    }

    linuxArm64()
    linuxX64()

    macosX64()
    macosArm64()

    mingwX64()

    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    @Suppress("OPT_IN_USAGE")
    wasmJs {
        browser()
    }
    @Suppress("OPT_IN_USAGE")
    wasmWasi {
        nodejs()
    }

    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosX64()
    watchosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                compileOnly("org.jetbrains.kotlin:kotlin-stdlib")
            }
        }

        val nonJvmMain by creating {
            dependsOn(commonMain)
        }
    }

    targets.onEach {
        if (it.platformType != KotlinPlatformType.jvm && it.platformType != KotlinPlatformType.common) {
            it.compilations.getByName("main").defaultSourceSet.dependsOn(sourceSets.getByName("nonJvmMain"))
        }
    }

    targets.all {
        compilations.all {
            compilerOptions.configure {
                optIn.add("kotlin.ExperimentalMultiplatform")
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}

// from https://github.com/Kotlin/kotlinx-datetime/blob/bc8adee2b9e3659e8e1c38fc09f3a4a1bdf85276/core/build.gradle.kts
tasks {
    val compileJavaModuleInfo by registering(JavaCompile::class) {
        val moduleName = "org.jetbrains.annotations" // this module's name
        val compileKotlinJvm by getting(KotlinCompile::class)
        val compileJava by getting(JavaCompile::class)
        val sourceDir = file("src/jvmMain/moduleInfo/")
        val targetDir = compileKotlinJvm.destinationDirectory.map { it.dir("../moduleInfo/") }

        // Use a Java 11 compiler for the module info.
        javaCompiler.set(project.javaToolchains.compilerFor { languageVersion.set(JavaLanguageVersion.of(11)) })

        // Always compile kotlin and java classes before the module descriptor.
        dependsOn(compileKotlinJvm)
        dependsOn(compileJava)

        // Add the module-info source file.
        source(sourceDir)

        // Set the task outputs and destination dir
        outputs.dir(targetDir)
        destinationDirectory.set(targetDir)

        // Configure JVM compatibility
        sourceCompatibility = JavaVersion.VERSION_1_9.toString()
        targetCompatibility = JavaVersion.VERSION_1_9.toString()

        // Set the Java release version.
        options.release.set(9)

        // Ignore warnings about using 'requires transitive' on automatic modules.
        // not needed when compiling with recent JDKs, e.g. 17
        options.compilerArgs.add("-Xlint:-requires-transitive-automatic")

        // Patch the compileKotlinJvm output classes into the compilation so exporting packages works correctly.
        options.compilerArgs.addAll(listOf("--patch-module", "$moduleName=${compileJava.destinationDirectory.get()}"))

        // Use the classpath of the compileKotlinJvm task.
        // Also ensure that the module path is used instead of classpath.
        classpath = compileKotlinJvm.libraries
        modularity.inferModulePath.set(true)
    }

    // Configure the JAR task so that it will include the compiled module-info class file.
    val jvmJar by existing(Jar::class) {
        manifest {
            attributes("Multi-Release" to true)
        }
        from(compileJavaModuleInfo.map { it.destinationDirectory }) {
            into("META-INF/versions/9/")
        }
    }

    val javaOnlySourcesJar by creating(Jar::class) {
        from(kotlin.sourceSets["jvmMain"].kotlin) {
            into("")
        }
        archiveClassifier.set("sources")
    }

    val allMetadataJar by existing(Jar::class) {
        archiveClassifier.set("common")
    }

    val javadocJar by creating(Jar::class) {
        from(javadoc)
        archiveClassifier.set("javadoc")
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(publishingUser)
            password.set(publishingPassword)
        }
    }
}

configurations {
    create("javaOnlySourcesElements") {
        copyAttributes(configurations.findByName("jvmSourcesElements")!!.attributes, attributes)
    }

    create("javadocElements") {
        attributes {
            attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category::class.java, Category.DOCUMENTATION))
            attribute(Bundling.BUNDLING_ATTRIBUTE, project.objects.named(Bundling::class.java, Bundling.EXTERNAL))
            attribute(DocsType.DOCS_TYPE_ATTRIBUTE, project.objects.named(DocsType::class.java, DocsType.JAVADOC))
            attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
        }
    }
}

artifacts {
    add("javaOnlySourcesElements", tasks.getByName("javaOnlySourcesJar"))
    add("javadocElements", tasks.getByName("javadocJar"))
}

publishing {
    val artifactBaseName = base.archivesName.get()
    configureMultiModuleMavenPublishing {
        val rootModule = module("rootModule") {
            mavenPublication {
                artifactId = artifactBaseName
                groupId = "org.jetbrains"
                configureKotlinPomAttributes(packaging = "jar")
            }
            variant("metadataApiElements") { suppressPomMetadataWarnings() }
            variant("metadataSourcesElementsFromJvm") {
                name = "metadataSourcesElements"
                configuration {
                    // to avoid clash in Gradle 8+ with metadataSourcesElements configuration with the same attributes
                    isCanBeConsumed = false
                }
                attributes {
                    copyAttributes(from = project.configurations["metadataSourcesElements"].attributes, to = this)
                }
                artifact(tasks["sourcesJar"]) {
                    classifier = "sources-common"
                }
            }
            variant("jvmApiElements")
            variant("jvmRuntimeElements") {
                configureVariantDetails { mapToMavenScope("runtime") }
            }
            variant("javaOnlySourcesElements")
            variant("javadocElements")
        }
        val targetModules = kotlin.targets.filter { it.targetName != "jvm" && it.targetName != "metadata" }.map { target ->
            val targetName = target.targetName
            module("${targetName}Module") {
                mavenPublication {
                    artifactId = "$artifactBaseName-$targetName"
                    groupId = "org.jetbrains"
                    configureKotlinPomAttributes(packaging = "klib")
                }
                variant("${targetName}ApiElements")
                if (configurations.findByName("${targetName}RuntimeElements") != null) {
                    variant("${targetName}RuntimeElements")
                }
                variant("${targetName}SourcesElements")
            }
        }

        // Makes all variants from accompanying artifacts visible through `available-at`
        rootModule.include(*targetModules.toTypedArray())
    }
}

fun MavenPublication.configureKotlinPomAttributes(
    packaging: String,
) {
    pom {
        this.packaging = packaging
        name.set("JetBrains Java Annotations")
        description.set("A set of annotations used for code inspection support and code documentation.")
        url.set("https://github.com/JetBrains/java-annotations")
        scm {
            url.set("https://github.com/JetBrains/java-annotations")
            connection.set("scm:git:git://github.com/JetBrains/java-annotations.git")
            developerConnection.set("scm:git:ssh://github.com:JetBrains/java-annotations.git")
        }
        licenses {
            license {
                name.set("The Apache Software License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("JetBrains")
                name.set("JetBrains Team")
                organization.set("JetBrains")
                organizationUrl.set("https://www.jetbrains.com")
            }
        }
    }
}

signing {
    sign(publishing.publications)
    signatories = GpgSignSignatoryProvider()
}

tasks.register("signAll") {
    dependsOn(tasks.withType<Sign>())
}