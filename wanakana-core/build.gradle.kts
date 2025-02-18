import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.greattusk"
version = "1.0.1"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.io.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)

            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
                // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
                implementation("org.junit.jupiter:junit-jupiter-engine:5.11.4")

            }
        }

    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED)
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

android {
    namespace = "io.github.greattusk.wanakana.common"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "wanakana-common", version.toString())

    pom {
        name = "Wanakana for KMP"
        description = "Fork of https://github.com/esnaultdev/wanakana-kt with minimal changes to make it KMP compatible"
        inceptionYear = "2025"
        url = "https://github.com/GreatTusk/wanakana-kmp/"
        licenses {
            license {
                name = "MIT"
                url = "https://opensource.org/licenses/MIT"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "GreatTusk"
                name = "GreatTusk"
                url = "https://github.com/GreatTusk"
            }
        }
        scm {
            url = "https://github.com/GreatTusk/wanakana-kmp/"
            connection = "scm:git:git://github.com/GreatTusk/wanakana-kmp.git"
            developerConnection = "scm:git:ssh://git@github.com/GreatTusk/wanakana-kmp.git"
        }
    }
}
