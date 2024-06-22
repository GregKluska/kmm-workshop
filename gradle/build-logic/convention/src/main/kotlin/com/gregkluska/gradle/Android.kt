package com.gregkluska.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidCommon(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = Versions.COMPILE_SDK

        defaultConfig {
            minSdk = Versions.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        configureKotlin()
    }
}