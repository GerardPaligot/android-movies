plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(rootProject.extra["targetSdk"] as Int)

  defaultConfig {
    minSdkVersion(rootProject.extra["minSdk"] as Int)
    targetSdkVersion(rootProject.extra["targetSdk"] as Int)
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    consumerProguardFile("consumer-consumer-proguard-rules.pro")
  }

  compileOptions {
    setSourceCompatibility(JavaVersion.VERSION_1_8)
    setTargetCompatibility(JavaVersion.VERSION_1_8)
  }

  lintOptions {
    isAbortOnError = true
  }
}

dependencies {
  implementation("androidx.lifecycle:lifecycle-extensions:${rootProject.extra["livedata"]}")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")
}
