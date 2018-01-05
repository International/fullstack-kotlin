package com.packtpub.route

import com.packtpub.handler.ApiHandler
import com.packtpub.util.WithLogging
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

class ApiRoutes(private val apiHandler: ApiHandler) : WithLogging() {
    @Bean
    fun apiRouter() =
        router {
            (accept(MediaType.APPLICATION_JSON_UTF8) and "/api").nest {
                "/projects".nest {
                    POST("/", apiHandler::handle)
                    GET("/", apiHandler::getProjects)
                    GET("/owners", apiHandler::getOwners)
                    GET("/byOwner/{name}", apiHandler::getByOwner)
                    GET("/{id}", apiHandler::getProject)
                }
            }
        }.filter{ req, next ->
            //LOG.debug(req)
            next.handle(req)
        }

}