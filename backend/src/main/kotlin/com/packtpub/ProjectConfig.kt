package com.packtpub

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@Configuration
class ProjectConfig(private val ctx: GenericApplicationContext){

    @Primary
    @Bean
    fun projectService(): ProjectService = ProjectServiceImpl(ctx.getBean(ProjectRepository::class.java))
}