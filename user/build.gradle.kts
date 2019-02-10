plugins {
  id("com.android.library")
  id("androidx.navigation.safeargs")
  id("kotlin-android")
  id("kotlin-android-extensions")
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

  sourceSets {
    getByName("main").res.srcDirs("src/main/res-user")
  }

  lintOptions {
    isAbortOnError = true
  }
}

dependencies {
  api(project(":shared"))
  implementation(project(":style"))

  implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat"]}")
  implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra["constraint"]}")
  implementation("androidx.recyclerview:recyclerview:${rootProject.extra["recyclerview"]}")
  implementation("androidx.lifecycle:lifecycle-extensions:${rootProject.extra["livedata"]}")
  kapt("androidx.lifecycle:lifecycle-compiler:${rootProject.extra["livedata"]}")

  implementation("android.arch.navigation:navigation-fragment-ktx:${rootProject.extra["navigation"]}")
  implementation("android.arch.navigation:navigation-ui-ktx:${rootProject.extra["navigation"]}")

  implementation("com.google.dagger:dagger:${rootProject.extra["dagger"]}")
  kapt("com.google.dagger:dagger-compiler:${rootProject.extra["dagger"]}")
  implementation("com.google.dagger:dagger-android-support:${rootProject.extra["dagger"]}")
  kapt("com.google.dagger:dagger-android-processor:${rootProject.extra["dagger"]}")
  implementation("com.google.android.material:material:${rootProject.extra["material"]}")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")
  implementation("io.reactivex.rxjava2:rxjava:${rootProject.extra["rxjava"]}")
  implementation("io.reactivex.rxjava2:rxandroid:${rootProject.extra["rxandroid"]}")
  implementation("com.jakewharton.timber:timber:${rootProject.extra["timber"]}")
  implementation("com.mikhaellopez:circularimageview:${rootProject.extra["circular"]}")

  testImplementation("junit:junit:4.12")
  androidTestImplementation("com.android.support.test:runner:1.0.2")
  androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
