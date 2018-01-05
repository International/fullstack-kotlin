package com.packtpub.handler

import com.packtpub.*
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import javax.validation.Validator

class ApiHandler(private val validator: Validator,
                 private val projectService: ProjectService) {
    fun handle(req: ServerRequest) = req.bodyToMono<ProjectDTO>()
        .map { project ->
            val violations = validator.validate(project)
            if (violations.isNotEmpty()) {
                project.fieldErrors = violations.map {
                    FieldErrorDTO(it.propertyPath.toString(), it.message)
                }
            }
            project
        }.flatMap {
        when (it.fieldErrors) {
            null -> ServerResponse.ok().body(Mono.just(
                projectService.saveProject(it.toProject()).toProjectDTO()
            ))
            else -> ServerResponse.unprocessableEntity().body(Mono.just(it))
        }
    }

    fun getProjects(req:ServerRequest) = ServerResponse.ok().body(Mono.just(
        projectService.fetchProjects().map { it.toProjectDTO() }
    ))

    fun getProject(req:ServerRequest) : Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()
        val projectDTO = projectService.fetchProject(id)?.toProjectDTO()
        return if(projectDTO != null) {
            ServerResponse.ok().body(Mono.just(projectDTO))
        } else {
            ServerResponse.notFound().build()
        }
    }

    fun getOwners(req:ServerRequest) : Mono<ServerResponse> =
        ServerResponse.ok().body(Mono.just(projectService.fetchAllOwners()))

    fun getByOwner(req:ServerRequest) : Mono<ServerResponse> {
        val name = req.pathVariable("name")
        return ServerResponse.ok().body(Mono.just(projectService.findByOwner(name)))
    }


}