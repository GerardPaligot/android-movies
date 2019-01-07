plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

val theMovieDatabaseApiKey: String by project

android {
  compileSdkVersion(rootProject.extra["targetSdk"] as Int)

  defaultConfig {
    minSdkVersion(rootProject.extra["minSdk"] as Int)
    targetSdkVersion(rootProject.extra["targetSdk"] as Int)
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    consumerProguardFile("consumer-consumer-proguard-rules.pro")

    buildConfigField("String", "API_KEY", theMovieDatabaseApiKey)
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
  api("androidx.lifecycle:lifecycle-extensions:${rootProject.extra["livedata"]}")

  api("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit"]}")
  api("com.squareup.retrofit2:adapter-rxjava2:${rootProject.extra["retrofit"]}")
  api("com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofit"]}")
  api("com.squareup.okhttp3:okhttp:${rootProject.extra["okhttp"]}")
  api("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["okhttp"]}")
  api("com.squareup.picasso:picasso:${rootProject.extra["picasso"]}")

  api("io.reactivex.rxjava2:rxjava:${rootProject.extra["rxjava"]}")
  api("com.f2prateek.rx.preferences2:rx-preferences:${rootProject.extra["rxpreference"]}")

  api("com.google.dagger:dagger:${rootProject.extra["dagger"]}")
  kapt("com.google.dagger:dagger-compiler:${rootProject.extra["dagger"]}")

  api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")

  testImplementation("junit:junit:4.12")
  androidTestImplementation("com.android.support.test:runner:1.0.2")
  androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
