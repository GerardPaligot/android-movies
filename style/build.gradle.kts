plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  compileSdkVersion(rootProject.extra["targetSdk"] as Int)

  defaultConfig {
    minSdkVersion(rootProject.extra["minSdk"] as Int)
    targetSdkVersion(rootProject.extra["targetSdk"] as Int)
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    consumerProguardFile("consumer-proguard-rules.pro")
  }

  dataBinding {
    isEnabled = true
  }

  compileOptions {
    setSourceCompatibility(JavaVersion.VERSION_1_8)
    setTargetCompatibility(JavaVersion.VERSION_1_8)
  }
}

dependencies {
  implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat"]}")
  implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra["constraint"]}")
  implementation("androidx.recyclerview:recyclerview:${rootProject.extra["recyclerview"]}")
  implementation("com.google.android.material:material:${rootProject.extra["material"]}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")
}
