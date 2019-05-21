plugins {
  id("com.android.library")
  id("androidx.navigation.safeargs")
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
  }

  dataBinding {
    isEnabled = true
  }

  compileOptions {
    setSourceCompatibility(JavaVersion.VERSION_1_8)
    setTargetCompatibility(JavaVersion.VERSION_1_8)
  }

  sourceSets {
    getByName("main").res.srcDirs("src/main/res-home", "src/main/res-library", "src/main/res-main")
  }

  lintOptions {
    isAbortOnError = true
  }
}

dependencies {
  implementation(project(":style"))
  implementation(project(":shared"))

  implementation(Dependencies.androidx.appCompat)
  implementation(Dependencies.androidx.constraint)
  implementation(Dependencies.androidx.recycler)
  implementation(Dependencies.androidx.material)
  implementation(Dependencies.androidx.liveData.extensions)
  kapt(Dependencies.androidx.liveData.compiler)
  implementation(Dependencies.androidx.paging.library)
  implementation(Dependencies.androidx.paging.ktx)

  implementation(Dependencies.androidx.navigation.fragment)
  implementation(Dependencies.androidx.navigation.ui)

  implementation(Dependencies.dagger.library)
  kapt(Dependencies.dagger.compiler)
  implementation(Dependencies.dagger.android)
  kapt(Dependencies.dagger.androidCompiler)

  implementation(Dependencies.kotlin)
  implementation(Dependencies.picasso)
  implementation(Dependencies.timber)
  implementation(Dependencies.rxAndroid)
}
