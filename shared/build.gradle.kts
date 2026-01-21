import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = false

            export(libs.mokoMvvm.core)
            export(libs.mokoMvvm.flow)
            export(libs.mokoMvvm.flow.swiftui)

            export(libs.mokoResources.resources)
            export(libs.mokoResources.graphics)
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
            api(libs.mokoMvvm.core)
            api(libs.mokoMvvm.flow)

            api(libs.mokoResources.resources)
            api(libs.mokoResources.resources.compose)

            implementation(libs.kotlinx.serialization.json)

            api(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.no.arg)
        }
        androidMain.dependencies {
            api(libs.mokoMvvm.core)
            api(libs.mokoMvvm.flow)
            api(libs.mokoMvvm.flow.compose)
        }
        iosMain.dependencies {
            api(libs.mokoMvvm.core)
            api(libs.mokoMvvm.flow)
            api(libs.mokoMvvm.flow.swiftui)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "app.xl.sportappkmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

multiplatformResources {
    resourcesPackage.set("app.xl.sportappkmp")
}