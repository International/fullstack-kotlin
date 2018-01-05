package com.packtpub

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

interface ProjectService {
    fun saveProject(project: Project): Project
    fun fetchProjects(): List<Project>
    fun fetchProject(id: Long): Project?
    fun findByOwner(owner: String): List<Project>
    fun fetchAllOwners(): List<String>
    fun fetchProjectsForView(): List<ProjectView>
}

const val mercy = "application/vnd.github.mercy-preview+json"
const val drax = "application/vnd.github.drax-preview+json"

internal class ProjectServiceImpl(private val projectRepository: ProjectRepository) : ProjectService {
    @Value("\${api.endpoint.url}") lateinit var endpoint: String
    override fun fetchProjects(): List<Project> = projectRepository.findAll().toList()
    override fun saveProject(project: Project): Project {
        fetchProject(project)
        return when (project.id) {
            null -> projectRepository.save(project)
            else -> projectRepository.findById(project.id)
                .map { persistedProject ->
                    projectRepository.save(persistedProject.copy(
                        name = project.name,
                        owner = project.owner,
                        url = project.url,
                        language = project.language
                    ))
                }.orElse(projectRepository.save(project))
        }
    }

    override fun fetchProject(id: Long) = projectRepository.findById(id).orElse(null)
    override fun findByOwner(owner: String): List<Project> = projectRepository.findByOwner(owner)
    override fun fetchAllOwners(): List<String> = projectRepository.retrieveAllOwners()
    override fun fetchProjectsForView(): List<ProjectView> = projectRepository.retrieveAllProjectsForView()

    private fun fetchProject(project: Project) {
        val webclient = WebClient.create(endpoint)
        webclient.get()
            .uri("/repos/${project.owner}/${project.name}")
            .accept(MediaType.parseMediaType(mercy), MediaType.parseMediaType(drax))
            .exchange()
            .flatMap { resp ->
                resp.bodyToMono<GithubApiDTO>()
            }
            .map { resp ->
                println(resp)
                resp
            }.subscribe()
    }
}
