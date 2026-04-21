plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
  //  id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.newsandhistory"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.newsandhistory"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.volley)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

// Source: https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation(libs.retrofit)
    // Source: https://mvnrepository.com/artifact/com.google.dagger/dagger-android
    implementation("com.google.dagger:dagger-android:2.59.1")

    // Source: https://mvnrepository.com/artifact/com.google.dagger/hilt-core
    runtimeOnly("com.google.dagger:hilt-core:2.59.1")
    // https://mvnrepository.com/artifact/androidx.hilt/hilt-navigation-compose
    // Source: https://mvnrepository.com/artifact/androidx.hilt/hilt-navigation-compose
    runtimeOnly("androidx.hilt:hilt-navigation-compose:1.3.0")
    implementation("androidx.preference:preference:1.2.1")
    implementation("androidx.datastore:datastore-preferences:1.1.7")
    // https://mvnrepository.com/artifact/org.http4k/http4k-format-moshi
    // Source: https://mvnrepository.com/artifact/org.http4k/http4k-format-moshi
    implementation("org.http4k:http4k-format-moshi:6.30.0.0")
    // Source: https://mvnrepository.com/artifact/com.squareup.moshi/moshi
    runtimeOnly("com.squareup.moshi:moshi:1.15.2")
    // Source: https://mvnrepository.com/artifact/io.coil-kt.coil3/coil-compose
    runtimeOnly("io.coil-kt.coil3:coil-compose:3.3.0")
    // https://mvnrepository.com/artifact/androidx.room/room-ktx
    runtimeOnly("androidx.room:room-ktx:2.8.3")
    implementation("com.google.dagger:hilt-android:2.57.1")
   // kapt("androidx.room:room-compiler:2.6.1")

    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")

    // For ViewModel injection
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Test dependencies
    testImplementation("com.google.dagger:hilt-android-testing:2.51")
    kaptTest("com.google.dagger:hilt-android-compiler:2.51")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51")

    // Source: https://mvnrepository.com/artifact/org.jetbrains.kotlin.kapt/org.jetbrains.kotlin.kapt.gradle.plugin
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:2.3.20")

}