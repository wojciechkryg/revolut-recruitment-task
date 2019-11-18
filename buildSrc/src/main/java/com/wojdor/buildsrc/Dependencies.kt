package com.wojdor.buildsrc

object Application {
    val id = "com.wojdor.revolutrecruitmenttask"
    val versionCode = 1
    val versionName = "1.0"
    val buildTools = "29.0.0"
    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28
}

object Version {
    val appcompat = "1.1.0"
    val constraintLayout = "1.1.3"
    val core = "1.1.0"
    val gradle = "3.5.2"
    val junit = "4.12"
    val kotlin = "1.3.60"
}

object Library {
    object Android {
        val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
        val core = "androidx.core:core-ktx:${Version.core}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    }

    object Test {
        val junit = "junit:junit:${Version.junit}"
    }

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
}

object Module {
    val app = ":app"
    val buildSrc = ":buildSrc"
    val common = ":common"
    val commonAndroid = ":common_android"
    val data = ":data"
    val domain = ":domain"
    val featureRates = ":feature_rates"
    val network = ":network"
    val repositoryRates = ":repository_rates"
}