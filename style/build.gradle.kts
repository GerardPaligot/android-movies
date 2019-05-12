plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Versions.targetSdk)

  defaultConfig {
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
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
  implementation(Dependencies.androidx.appCompat)
  implementation(Dependencies.androidx.constraint)
  implementation(Dependencies.androidx.recycler)
  implementation(Dependencies.androidx.material)
  implementation(Dependencies.circular)
  implementation(Dependencies.kotlin)
}
