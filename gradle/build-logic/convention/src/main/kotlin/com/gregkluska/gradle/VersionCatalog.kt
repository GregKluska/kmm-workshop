package com.gregkluska.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.findPluginId(plugin: LibsPlugin): String {
    return findPlugin(plugin.alias).get().get().pluginId
}

internal enum class LibsPlugin(val alias: String) {
    AndroidApplication("androidApplication"),
    AndroidLibrary("androidLibrary"),
    KotlinAndroid("kotlinAndroid"),
    KotlinMultiplatform("kotlinMultiplatform"),
    KSP("ksp"),
    ComposeCompiler("compose-compiler")
}
