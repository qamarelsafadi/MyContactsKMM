buildscript {
    dependencies {
        classpath(libs.gradle.plugin)
    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.2.0-alpha06").apply(false)
    id("com.android.library").version("8.2.0-alpha06").apply(false)
    kotlin("android").version("1.8.20").apply(false)
    kotlin("multiplatform").version("1.8.20").apply(false)
    id("org.jetbrains.compose").apply(false)

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
