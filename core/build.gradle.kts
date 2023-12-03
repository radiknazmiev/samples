plugins {
    id("kotlin-kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.nazmiev.radik.vkclient.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation("androidx.test:monitor:1.6.1")
    val room_version = "2.5.2"
    val RETROFIT_VERSION = "2.9.0"

    implementation("androidx.core:core-ktx:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")

    api("androidx.work:work-runtime-ktx:2.8.1")

    api("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    api("androidx.constraintlayout:constraintlayout:2.1.4")

    //compose
    api("androidx.compose.compiler:compiler:1.3.2")
    api("androidx.compose.ui:ui:1.3.2")
    api("androidx.compose.material:material:1.3.1")
    api("androidx.compose.ui:ui-tooling-preview:1.4.3")
    api("androidx.compose.runtime:runtime-livedata:1.3.2")
    api("androidx.activity:activity-compose:1.7.2")
    api("androidx.compose.material3:material3:1.1.2")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //Room
    api("androidx.room:room-runtime:$room_version")
    api("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:$RETROFIT_VERSION")
    api("com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION")
    api("com.squareup.retrofit2:adapter-rxjava2:$RETROFIT_VERSION")
    api("com.squareup.retrofit2:converter-scalars:$RETROFIT_VERSION")
    api("com.squareup.okhttp3:logging-interceptor:4.10.0")

    api("io.coil-kt:coil-compose:2.2.2")
}

hilt {
    enableAggregatingTask = true
}