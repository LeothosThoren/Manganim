@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.manganimu.application)
    alias(libs.plugins.manganimu.compose)
    alias(libs.plugins.manganimu.hilt)
}

android {
    namespace = "com.thoren.manganimu"

    defaultConfig {
        applicationId = "com.thoren.manganimu"
        versionName = "0.4.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
        versionCode = (System.currentTimeMillis() / 1000).toInt()

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.data.anime)

    implementation(projects.feature.anime)
    implementation(projects.feature.manga)
    implementation(projects.feature.music)
    implementation(projects.feature.option)

    implementation(libs.androidx.activity.compose)
}