import com.gregkluska.gradle.configureDetekt
import com.gregkluska.gradle.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }

        kotlin {
            applyDefaultHierarchyTemplate()
            jvm()

            if (pluginManager.hasPlugin("com.android.library")) {
                androidTarget()
            }

            iosArm64()
            iosSimulatorArm64()

            targets.configureEach {
                compilations.configureEach {
                    compileTaskProvider.configure {
                        compilerOptions {
                            freeCompilerArgs.add("-Xexpect-actual-classes")
                        }
                    }
                }
            }
        }

        configureKotlin()
        configureDetekt()
    }
}

private fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) =
    extensions.configure<KotlinMultiplatformExtension>(action)