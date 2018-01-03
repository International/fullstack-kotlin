package com.packtpub.route

import com.packtpub.HelloSayer
import com.packtpub.ProjectService
import com.packtpub.util.htmlView
import com.packtpub.util.json
import com.packtpub.views.index
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

class ViewRoutes(private val projectService: ProjectService) {
    private val links = mapOf(
            "Kotlin" to "https://github.com/JetBrains/kotlin",
            "Spring" to "https://github.com/spring-projects/spring-framework",
            "React" to "https://github.com/facebook/react",
            "Full Stack Development" to "https://github.com/Xantier/fullstack-kotlin"
    )

    @Bean
    fun viewRouter() =
            router {
                accept(MediaType.TEXT_HTML).nest {
                    GET("/hello") { req ->
                        val name = req.queryParam("name").orElse("User")
                        ServerResponse.ok()
                            .htmlView(Mono.just(index(name)))
                    }
                }
                resources("/**", ClassPathResource("/static"))
            }
}