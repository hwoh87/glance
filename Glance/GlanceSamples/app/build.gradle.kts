plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.glancesamples"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.glancesamples"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // 필수: Glance 위젯 라이브러리
    implementation("androidx.glance:glance-appwidget:1.1.1")

    // 추천: Material3 테마 지원
    implementation("androidx.glance:glance-material3:1.1.0")

    // 필수: 상태 관리용 DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // 추천: 백그라운드 작업용 WorkManager
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // Kotlin 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // AndroidX Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
}
