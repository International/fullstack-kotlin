plugins {
    id("org.jetbrains.kotlin.plugin.spring") version "1.1.51"
}
apply {
    plugin("org.springframework.boot")
}
dependencies {
    val springBootVersion: String = parent!!.properties["springBootVersion"] as String
    compile(project(":backend:component"))
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
}