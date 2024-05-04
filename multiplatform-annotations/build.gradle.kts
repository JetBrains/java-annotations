import org.gradle.jvm.tasks.Jar

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
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}

val jvmJar by tasks.getting(Jar::class) {
    into ("META-INF/versions/9") {
        from (project(":module-info").sourceSets["main"].output) {
            include("module-info.class")
        }
    }
    manifest.attributes("Multi-Release" to true)
}

val jvmSourcesJar by tasks.getting(Jar::class) {
    into ("META-INF/versions/9") {
        from (project(":module-info").sourceSets["main"].output) {
            include("module-info.class")
        }
    }
}