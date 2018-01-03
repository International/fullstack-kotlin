package com.packtpub

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@Configuration
class ProjectConfig(ctx: GenericApplicationContext){

    @Bean
    fun helloSayer():ProjectService = ProjectServiceImpl()
}