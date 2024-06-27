import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.gregkluska.gradle.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("RootConventionPlugin") {
            id = "workshop.gradle.root"
            implementationClass = "RootConventionPlugin"
        }

        register("AndroidApplicationConventionPlugin") {
            id = "workshop.gradle.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("AndroidLibraryConventionPlugin") {
            id = "workshop.gradle.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("KotlinAndroidConventionPlugin") {
            id = "workshop.gradle.kotlin.android"
            implementationClass = "KotlinAndroidConventionPlugin"
        }


        register("KotlinMultiplatformConventionPlugin") {
            id = "workshop.gradle.kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }

    }
}
//TODO: Fix it below
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.9"
}