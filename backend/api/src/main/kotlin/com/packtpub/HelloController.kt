package com.packtpub

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val helloSayer: HelloSayer) {

    val links = mapOf(
            "Kotlin" to "https://github.com/JetBrains/kotlin",
            "Spring" to "https://github.com/spring-projects/spring-framework",
            "React" to "https://github.com/facebook/react",
            "Full Stack Development" to "https://github.com/Xantier/fullstack-kotlin"
    )

    @GetMapping(produces = arrayOf("text/html"))
    fun sayHello(@RequestParam("name", required = false) name:String?):String {
        return createHTML(true).html {
            head {
                title = "Full Stack Kotlin"
            }
            body {
                h4 {
                    + helloSayer.sayHello(name ?: "User")
                }
                p {
                    + "Welcome to Full Stack Kotlin"
                }
                p {
                    + "Our Resources:"
                    ul {
                        links.map { (name,url) ->
                            li {
                                a(url) {
                                    target = ATarget.blank
                                    +name
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}