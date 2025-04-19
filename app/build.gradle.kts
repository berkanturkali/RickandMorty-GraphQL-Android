plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.apollo.runtime)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlinx.serialization)
}

apollo {
    service("service") {
        packageName.set("com.example")
    }
}

android {
    namespace = "com.android.example.rickandmortygraphql"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.android.example.rickandmortygraphql"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "GRAPHQL_BASE_URL", "\"https://rickandmortyapi.com/graphql/\"")
    }

    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //apollo
    implementation(libs.apollo.runtime)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //navigation compose
    implementation(libs.androidx.navigation.compose)

    //kotlinx serialization json
    implementation(libs.kotlinx.serialization.json)

    //timber
    implementation(libs.timber)

    //hilt navigation compose
    implementation(libs.hilt.navigation.compose)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

}