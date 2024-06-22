package com.gregkluska.gradle

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.LineEnding
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
    with(pluginManager) {
        apply(libs.findPlugin("spotless").get().get().pluginId)
    }

    spotless {
        // Workaround for https://github.com/diffplug/spotless/issues/1644
        lineEndings = LineEnding.PLATFORM_NATIVE

        val ktlintVersion = libs.findVersion("ktlint").get().toString()

        kotlin {
            target("src/**/*.kt")
            ktlint(ktlintVersion)
            trimTrailingWhitespace()
            endWithNewline()
        }

        kotlinGradle {
            target("*.kts")
            ktlint(ktlintVersion)
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) =
    extensions.configure<SpotlessExtension>(action)