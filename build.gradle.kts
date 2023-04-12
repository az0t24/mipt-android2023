buildscript {
    val composeUiVersion by extra("1.4.0")
    val lifecycleVersion by extra("2.6.1")
    val ktorVersion by extra("2.2.3")
    val navigationVersion by extra("2.5.3")
    val roomVersion by extra("2.5.0")
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jetbrains.kotlin.kapt") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
}