
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.1.51"
}

buildscript {
    val springBootVersion: String = properties["springBootVersion"] as String

    repositories {
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("https://repo.spring.io/snapshot") }

    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

subprojects {
    val kotlinVersion = properties["kotlinVersion"] as String

    repositories {
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("https://repo.spring.io/snapshot") }
    }
 
    plugins {
        kotlin("jvm") version kotlinVersion
    }

    apply {
        plugin("kotlin")
    }

    dependencies {
        compile(kotlin("stdlib-jre8"))
        compile(kotlin("reflect"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

