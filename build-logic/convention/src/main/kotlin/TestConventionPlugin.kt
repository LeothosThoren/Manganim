import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.thoren.manganimu.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureAndroid(this)
                defaultConfig.targetSdk = SdkVersions.TARGET_SDK
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("androidx.test.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.test.junit4").get())
            }
        }
    }
}