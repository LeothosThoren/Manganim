import com.android.build.api.dsl.CommonExtension
import com.thoren.manganimu.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val extension = extensions.getByName("android") as CommonExtension
            configureCompose(extension)
        }
    }
}