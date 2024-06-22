import com.android.build.api.dsl.LibraryExtension
import com.gregkluska.gradle.configureAndroidCommon
import com.gregkluska.gradle.configureDetekt
import com.gregkluska.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("android-library").get().get().pluginId)
            apply(libs.findPlugin("jetbrains-kotlin-android").get().get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidCommon(this)
        }

//        configureSpotless()
        configureDetekt()
    }

}