// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  extra.apply {
    set("targetSdk", 28)
    set("minSdk", 17)

    set("appcompat", "1.0.2")
    set("material", "1.1.0-alpha02")
    set("constraint", "1.1.3")
    set("recyclerview", "1.0.0")
    set("cardview", "1.0.0")
    set("navigation", "1.0.0-alpha09")
    set("livedata", "2.0.0")

    set("retrofit", "2.5.0")
    set("okhttp", "3.12.1")
    set("picasso", "2.71828")
    set("timber", "4.7.1")
    set("circular", "3.2.0")
    set("rxjava", "2.1.16")
    set("rxandroid", "2.1.0")
    set("rxpreference", "2.0.0")
    set("dagger", "2.16")

    set("kotlin", "1.3.11")
  }

  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:3.2.1")
    classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra["navigation"]}")
    classpath(kotlin("gradle-plugin", rootProject.extra["kotlin"] as String))
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks.register(name = "clean", type = Delete::class) {
  delete(rootProject.buildDir)
}