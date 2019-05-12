// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath(Dependencies.androidPlugin)
    classpath(Dependencies.androidx.navigation.plugin)
    classpath(kotlin("gradle-plugin", Versions.kotlin))
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