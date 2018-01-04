plugins {
    id("org.jetbrains.kotlin.plugin.spring") version embeddedKotlinVersion
}

dependencies {
    val springBootVersion : String = parent!!.properties["springBootVersion"] as String
    val postgresVersion : String = parent!!.properties["postgresVersion"] as String
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("org.postgresql:postgresql:$postgresVersion")
}