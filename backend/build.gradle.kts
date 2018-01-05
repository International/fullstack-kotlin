import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    val kotlinVersion = "1.1.51"
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion

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
repositories {
    mavenCentral()
    maven { setUrl("https://repo.spring.io/milestone") }
    maven { setUrl("https://repo.spring.io/snapshot") }
}

apply {
    val kotlinVersion = "1.1.51"

    plugin("kotlin")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")

}

dependencies {
    compile(kotlin("stdlib-jre8"))
    compile(kotlin("reflect"))

    val postgresVersion: String = properties["postgresVersion"] as String
    val hibernateValidatorVersion = properties["hibernateValidatorVersion"] as String
    val kotlinxHtmlVersion: String = properties["kotlinxHtmlVersion"] as String

    compile("org.springframework.boot:spring-boot-starter")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("org.springframework.boot:spring-boot-devtools")
    compile("org.springframework.security:spring-security-core")
    compile("org.springframework.security:spring-security-config")
    compile("org.springframework.security:spring-security-webflux")

    compile("org.postgresql:postgresql:$postgresVersion")

    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
    compile("org.hibernate:hibernate-validator:$hibernateValidatorVersion")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

