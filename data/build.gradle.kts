import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.version.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.version.get())
}
kotlin {
    compilerOptions {
        // Convert the version string to the correct JvmTarget enum
        jvmTarget.set(JvmTarget.fromTarget(libs.versions.java.version.get()))
    }
}
dependencies {
    implementation(libs.kotlin.stdlib)
}