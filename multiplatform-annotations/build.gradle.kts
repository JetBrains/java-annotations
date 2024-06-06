import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.9.22"
}

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
    }

    /**
     * @Nls contains enum class. Kotlin automatically generates
     * `.entries` method for every enum and adds @NotNull to it.
     * This disables `.entries` generation.
     */
    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-XXLanguage:-EnumEntries")
            }
        }
    }
}

// from https://github.com/Kotlin/kotlinx-datetime/blob/bc8adee2b9e3659e8e1c38fc09f3a4a1bdf85276/core/build.gradle.kts
tasks {
    val compileJavaModuleInfo by registering(JavaCompile::class) {
        val moduleName = "org.jetbrains.annotations.multiplatform" // this module's name
        val compileKotlinJvm by getting(KotlinCompile::class)
        val sourceDir = file("src/jvmMain/moduleInfo/")
        println("sourceDir: $sourceDir")
        val targetDir = compileKotlinJvm.destinationDirectory.map { it.dir("../moduleInfo/") }
        println("targetDir: $targetDir")

        // Use a Java 11 compiler for the module info.
        javaCompiler.set(project.javaToolchains.compilerFor { languageVersion.set(JavaLanguageVersion.of(11)) })

        // Always compile kotlin classes before the module descriptor.
        dependsOn(compileKotlinJvm)

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
        options.compilerArgs.addAll(listOf("--patch-module", "$moduleName=${compileKotlinJvm.destinationDirectory.get()}"))

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
}