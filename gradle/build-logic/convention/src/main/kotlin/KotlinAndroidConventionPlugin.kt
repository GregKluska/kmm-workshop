import com.android.build.api.dsl.LibraryExtension
import com.gregkluska.gradle.configureAndroidCommon
import com.gregkluska.gradle.configureDetekt
import com.gregkluska.gradle.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KotlinAndroidConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.android")
        }

        configureKotlin()
        configureDetekt()
    }

}