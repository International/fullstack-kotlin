package com.packtpub.views

import com.packtpub.ProjectDTO
import com.packtpub.ProjectView
import kotlinx.html.*
import kotlinx.html.stream.createHTML

fun index(header: String, projects: List<ProjectView>):String {
    return createHTML(true).html {
        head {
            title = "Full Stack Kotlin"
            styleLink("/static/css/hello.css")
        }
        body {
            h4 {
                +header
            }
            p {
                +"Welcome to Full Stack Kotlin"
            }
            p {
                +"Our Resources:"
                ul {
                    projects.map { (name, url, owner) ->
                        li {
                            a(url) {
                                target = ATarget.blank
                                +"$name by $owner"
                            }
                        }
                    }
                }
            }
            script(src = "/static/js/hello.js")
        }
    }

}