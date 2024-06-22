import com.gregkluska.gradle.configureDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class RootConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        tasks.register("install", Copy::class.java) {
            from("${rootDir}/scripts/pre-commit")
            into("${rootDir}/.git/hooks")
        }

//        configureSpotless()
        configureDetekt()
    }
}