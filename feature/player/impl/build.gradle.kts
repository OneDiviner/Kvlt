plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.example.impl"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.compose.runtime)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material3)
                implementation(libs.compose.ui)
                implementation(libs.compose.components.resources)
                implementation(libs.compose.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.coil.core)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.okhttp)
                implementation(libs.orbit.mvi.core)
                implementation(libs.orbit.mvi.viewmodel)
                implementation(libs.orbit.mvi.compose)
                implementation(libs.kotlinx.serialization.json)
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.chrisbanes.haze)

                implementation(projects.feature.player.api)

                implementation(projects.core.resources)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.media3.exoplayer)
                implementation(libs.androidx.media3.exoplayer.dash)
                implementation(libs.androidx.media3.ui)
                implementation(libs.androidx.media3.ui.compose)
                implementation(libs.androidx.media3.session)
                implementation(libs.androidx.window)
            }
        }
    }

}