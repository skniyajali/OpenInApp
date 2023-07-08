@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.niyaj.openinappold"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.niyaj.openinappold"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(libs.core.ktx)

    implementation(libs.ui)
    implementation(libs.ui.util)
    implementation(libs.ui.graphics)
    implementation(libs.material3)
    implementation(libs.material.icons)
    implementation(libs.ui.tooling.preview)
    implementation(libs.activity.compose)

    // ViewModel
    implementation(libs.viewmodel.ktx)
    implementation(libs.viewmodel.compose)
    implementation(libs.runtime.compose)
    implementation(libs.runtime.ktx)
    // Saved state module for ViewModel
    implementation(libs.viewmodel.savedstate)
    // Annotation processor
    implementation(libs.common.java8)

    // Compose dependencies
    implementation(libs.navigation.compose)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // For testing
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.coroutines.test)

    //Hilt Work
//    implementation(libs.hilt.work)
//    kapt(libs.hilt.compiler)

    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android)

    // Dagger & Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.dagger.compiler)

    // For testing
    androidTestImplementation(libs.hilt.android.testing)

    testImplementation(libs.hilt.android.testing)

    // Timber
    implementation(libs.timber)

    //Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    // Local unit tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Coil Compose
    implementation(libs.coil)

    // Vico Chart
    implementation(libs.vico.core)
    implementation(libs.vico.compose)
    implementation(libs.vico.m3)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}