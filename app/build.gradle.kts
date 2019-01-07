plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(rootProject.extra["targetSdk"] as Int)

  defaultConfig {
    applicationId = "com.paligot.movies"
    minSdkVersion(rootProject.extra["minSdk"] as Int)
    targetSdkVersion(rootProject.extra["targetSdk"] as Int)
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
}

dependencies {
  implementation(project(":home"))
  implementation(project(":user"))
  implementation(project(":navigation"))
  implementation(project(":style"))

  implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat"]}")
  implementation("com.google.android.material:material:${rootProject.extra["material"]}")

  implementation("android.arch.navigation:navigation-fragment-ktx:${rootProject.extra["navigation"]}")
  implementation("android.arch.navigation:navigation-ui-ktx:${rootProject.extra["navigation"]}")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin"]}")

  testImplementation("junit:junit:4.12")
  androidTestImplementation("com.android.support.test:runner:1.0.2")
  androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
