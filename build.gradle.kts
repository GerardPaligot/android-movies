// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra.set("kotlin", "1.3.11")

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlin"]}")
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