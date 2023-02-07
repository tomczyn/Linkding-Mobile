buildscript {
    repositories {
        google()
        mavenCentral()
    }

}

plugins {
    id("com.android.application").version("7.4.1").apply(false)
    id("com.android.library").version("7.4.1").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    alias(libs.plugins.ktlint) apply false
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)

    repositories {
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
