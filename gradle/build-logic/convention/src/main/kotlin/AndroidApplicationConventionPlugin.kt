import com.android.build.api.dsl.ApplicationExtension
import com.gregkluska.gradle.Versions
import com.gregkluska.gradle.configureAndroidCommon
import com.gregkluska.gradle.configureDetekt
import com.gregkluska.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-application").get().get().pluginId)
            apply(libs.findPlugin("jetbrains-kotlin-android").get().get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            configureAndroidCommon(this)

            defaultConfig {
                targetSdk = Versions.TARGET_SDK
            }
        }

//        configureSpotless()
        configureDetekt()
    }

}