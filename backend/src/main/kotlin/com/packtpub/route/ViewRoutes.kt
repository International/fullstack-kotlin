package com.packtpub.route

import com.packtpub.HelloSayer
import com.packtpub.ProjectService
import com.packtpub.handler.ViewHandler
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
import java.net.URI

class ViewRoutes(private val projectService: ProjectService,
                 private val viewHandler: ViewHandler) {
    private val links = mapOf(
            "Kotlin" to "https://github.com/JetBrains/kotlin",
            "Spring" to "https://github.com/spring-projects/spring-framework",
            "React" to "https://github.com/facebook/react",
            "Full Stack Development" to "https://github.com/Xantier/fullstack-kotlin"
    )

    @Bean
    fun viewRouter() =
            router {
                "/" {
                    ServerResponse.temporaryRedirect(URI("/projects/view")).build()
                }
                accept(MediaType.TEXT_HTML).nest {
                    ("/projects" and accept(MediaType.TEXT_HTML)).nest {
                        GET("/view", viewHandler::handle)
                    }
                }
                resources("/**", ClassPathResource("/static"))
            }
}