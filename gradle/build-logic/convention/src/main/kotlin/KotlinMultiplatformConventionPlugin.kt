import com.gregkluska.gradle.LibsPlugin
import com.gregkluska.gradle.findPluginId
import com.gregkluska.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPluginId(LibsPlugin.KotlinMultiplatform))
        }

        kotlin {
            if (pluginManager.hasPlugin("")) {
                androidTarget()
            }

            iosArm64()
            iosSimulatorArm64()
        }
    }
}

private fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) =
    extensions.configure<KotlinMultiplatformExtension>(action)