// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Project-level build.gradle.kts
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
    }
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

