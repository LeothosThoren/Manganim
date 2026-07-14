package com.thoren.manganimu

import SdkVersions
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

internal fun configureAndroid(commonExtension: CommonExtension) {
    commonExtension.apply {
        compileSdk = SdkVersions.COMPILE_SDK

        defaultConfig.apply {
            minSdk = SdkVersions.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
    }
}