plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

kotlin {
//    androidTarget {
//        compilations.all {
//            kotlinOptions {
//                jvmTarget = "17"
//            }
//        }
//    }
    androidTarget()

    listOf(
        iosX64(),
    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.inject.runtime.kmp)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

dependencies {
    kspCommonMainMetadata(libs.kotlin.inject.compiler)
}

android {
    namespace = "com.gregkluska.kmmworkshop"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
