import com.android.build.api.dsl.ApplicationExtension
import com.thoren.manganimu.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(commonExtension = this)
                defaultConfig.targetSdk = SdkVersions.TARGET_SDK
                defaultConfig.minSdk = SdkVersions.MIN_SDK
            }
        }
    }
}