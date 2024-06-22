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

        register("AndroidApplicationComposeConventionPlugin") {
            id = "workshop.gradle.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("AndroidLibraryConventionPlugin") {
            id = "workshop.gradle.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("AndroidLibraryComposeConventionPlugin") {
            id = "workshop.gradle.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("AndroidHiltConventionPlugin") {
            id = "workshop.gradle.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("JvmLibraryConventionPlugin") {
            id = "workshop.gradle.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
//TODO: Fix it below
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.9"
}