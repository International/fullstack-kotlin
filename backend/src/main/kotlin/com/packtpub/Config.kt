package com.packtpub

import com.packtpub.handler.ApiHandler
import com.packtpub.handler.ExceptionHandler
import com.packtpub.handler.ViewHandler
import com.packtpub.route.ApiRoutes
import com.packtpub.route.ViewRoutes
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity


@SpringBootApplication
@EnableWebFluxSecurity
class Config

fun main(args: Array<String>){
    val application = SpringApplication(Config::class.java)
    application.addInitializers(ApplicationContextInitializer<GenericApplicationContext> { ctx ->
        beans{
            bean { ViewHandler(ref()) }
            bean { ViewRoutes(ref(), ref()) }
            bean { ApiHandler(ref(), ref())}
            bean { ApiRoutes(ref()) }
            bean<ExceptionHandler>()
            securityBeans()
        }.initialize(ctx)
    })
    application.run(*args)
}