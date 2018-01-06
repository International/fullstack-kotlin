import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

buildscript {
    val kotlinVersion = properties["kotlinVersion"] as String
    repositories() {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

apply {
    plugin("kotlin2js")
}

repositories {
    jcenter()
}

val kotlinVersion = properties["kotlinVersion"] as String

dependencies {
    "compile"(kotlin("stdlib-js", kotlinVersion))
}

val compileKotlin2Js : Kotlin2JsCompile by tasks

compileKotlin2Js.kotlinOptions {
    sourceMap = true
    metaInfo = true
    freeCompilerArgs = listOf("-Xcoroutines=enable")
    outputFile = "${project.buildDir.path}/js/index.js"
    main = "call"
    moduleKind = "commonjs"
}