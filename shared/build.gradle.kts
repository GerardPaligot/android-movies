plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

val theMovieDatabaseApiKey: String by project
val theMovieDatabaseToken: String by project

android {
  compileSdkVersion(Versions.targetSdk)

  defaultConfig {
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    consumerProguardFile("consumer-consumer-proguard-rules.pro")

    buildConfigField("String", "API_KEY", theMovieDatabaseApiKey)
    buildConfigField("String", "TOKEN_V4", theMovieDatabaseToken)
  }

  compileOptions {
    setSourceCompatibility(JavaVersion.VERSION_1_8)
    setTargetCompatibility(JavaVersion.VERSION_1_8)
  }

  dataBinding {
    isEnabled = true
  }

  lintOptions {
    isAbortOnError = true
  }
}

dependencies {
  implementation(Dependencies.androidx.liveData.extensions)

  api(Dependencies.retrofit.library)
  api(Dependencies.retrofit.rxJava)
  api(Dependencies.retrofit.gson)
  api(Dependencies.okhttp.library)
  api(Dependencies.okhttp.logging)
  api(Dependencies.picasso)

  implementation(Dependencies.rxJava)
  api(Dependencies.rxPreferences)

  implementation(Dependencies.dagger.library)
  kapt(Dependencies.dagger.compiler)

  implementation(Dependencies.kotlin)
}
