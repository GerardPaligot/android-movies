plugins {
  id("com.android.dynamic-feature")
  id("androidx.navigation.safeargs")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Versions.targetSdk)

  defaultConfig {
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
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

  sourceSets {
    getByName("main").res.srcDirs("src/main/res-user")
  }

  lintOptions {
    isAbortOnError = true
  }
}

dependencies {
  implementation(project(":app"))
  implementation(project(":shared"))
  implementation(project(":style"))

  implementation(Dependencies.androidx.appCompat)
  implementation(Dependencies.androidx.constraint)
  implementation(Dependencies.androidx.recycler)
  implementation(Dependencies.androidx.material)
  implementation(Dependencies.androidx.livedata.extensions)
  kapt(Dependencies.androidx.livedata.compiler)

  implementation(Dependencies.androidx.navigation.fragment)
  implementation(Dependencies.androidx.navigation.ui)

  implementation(Dependencies.circular)

  implementation(Dependencies.dagger.library)
  kapt(Dependencies.dagger.compiler)
  implementation(Dependencies.dagger.android)
  kapt(Dependencies.dagger.androidCompiler)

  implementation(Dependencies.kotlin)
  implementation(Dependencies.timber)
  implementation(Dependencies.rxJava)
  implementation(Dependencies.rxAndroid)
}
