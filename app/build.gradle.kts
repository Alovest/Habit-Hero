plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.habithero"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.habithero"
        minSdk = 26
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
    buildFeatures {
        compose = true
    }
}

dependencies {
implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.room.ktx)
    //    implementation(libs.androidx.compose.runtime.livedata)
//    implementation(libs.androidx.navigation.runtime.ktx)
//    implementation(libs.volley)
    val room_version = "2.8.3"
    val compose_version = "1.4.3"
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.room:room-ktx:$room_version")
    // Gif:
    implementation("io.coil-kt:coil-gif:2.3.0")
    implementation("io.coil-kt:coil-compose:2.3.0")

    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.room:room-rxjava2:$room_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$room_version")
    implementation("androidx.room:room-rxjava3:$room_version")

    implementation("androidx.room:room-guava:$room_version")
    implementation("androidx.compose.material:material:$compose_version")
    testImplementation("androidx.room:room-testing:$room_version")
// coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    // Жизненный цикл: ViewModel и LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$room_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$room_version")
    //koin DI
    implementation("io.insert-koin:koin-androidx-compose:3.4.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}