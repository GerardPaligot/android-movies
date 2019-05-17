plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Versions.targetSdk)

  defaultConfig {
    applicationId = "com.paligot.movies"
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
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

  dynamicFeatures.add(":user")
}

dependencies {
  implementation(project(":home"))
  implementation(project(":style"))

  implementation(Dependencies.androidx.appCompat)
  implementation(Dependencies.androidx.material)
  implementation(Dependencies.androidx.liveData.extensions)
  kapt(Dependencies.androidx.liveData.compiler)

  implementation(Dependencies.androidx.navigation.fragment)
  implementation(Dependencies.androidx.navigation.ui)

  implementation(Dependencies.kotlin)
}
