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

  implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat"]}")
  implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra["constraint"]}")
  implementation("androidx.recyclerview:recyclerview:${rootProject.extra["recyclerview"]}")
  implementation("com.google.android.material:material:${rootProject.extra["material"]}")
  implementation("androidx.lifecycle:lifecycle-extensions:${rootProject.extra["livedata"]}")
  kapt("androidx.lifecycle:lifecycle-compiler:${rootProject.extra["livedata"]}")

  implementation("android.arch.navigation:navigation-fragment-ktx:${rootProject.extra["navigation"]}")
  implementation("android.arch.navigation:navigation-ui-ktx:${rootProject.extra["navigation"]}")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")
  implementation("com.squareup.picasso:picasso:${rootProject.extra["picasso"]}")
  implementation("com.jakewharton.timber:timber:${rootProject.extra["timber"]}")

  testImplementation("junit:junit:4.12")
  androidTestImplementation("com.android.support.test:runner:1.0.2")
  androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
