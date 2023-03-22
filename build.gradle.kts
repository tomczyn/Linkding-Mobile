buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application").version(libs.versions.androidGradlePlugin.get()).apply(false)
    id("com.android.library").version(libs.versions.androidGradlePlugin.get()).apply(false)
    kotlin("android").version(libs.versions.kotlin.get()).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
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
