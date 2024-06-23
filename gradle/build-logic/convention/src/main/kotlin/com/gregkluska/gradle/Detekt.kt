package com.gregkluska.gradle

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named

internal fun Project.configureDetekt() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }

    detekt {
        toolVersion = libs.findVersion("detekt").get().toString()
        config.setFrom("$rootDir/detekt.yml")
        buildUponDefaultConfig = true
        autoCorrect = true
    }

    tasks.named<Detekt>("detekt") {
        reports {
            html.required.set(true)
        }
    }

    dependencies {
        add("detektPlugins", libs.findLibrary("detekt-formatting").get())
    }
}

private fun Project.detekt(action: DetektExtension.() -> Unit) =
    extensions.configure<DetektExtension>(action)