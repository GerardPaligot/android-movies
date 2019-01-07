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
    consumerProguardFile("consumer-proguard-rules.pro")
    vectorDrawables.useSupportLibrary = true
  }

  dataBinding {
    isEnabled = true
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
  implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat"]}")
  implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra["constraint"]}")
  implementation("androidx.recyclerview:recyclerview:${rootProject.extra["recyclerview"]}")
  implementation("com.google.android.material:material:${rootProject.extra["material"]}")
  implementation("com.mikhaellopez:circularimageview:${rootProject.extra["circular"]}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")
}
