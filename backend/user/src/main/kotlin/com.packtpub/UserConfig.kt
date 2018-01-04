package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.http.HttpMethod
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.config.web.server.ServerHttpSecurity

fun BeanDefinitionDsl.securityBeans() {
    bean<SecurityWebFilterChain> {
        ServerHttpSecurity.http().authorizeExchange()
            .pathMatchers(HttpMethod.GET, "/api/projects/**").permitAll()
            .pathMatchers(HttpMethod.POST, "/api/projects/**").hasRole("ADMIN")
            .pathMatchers(HttpMethod.GET, "/projects/**").hasRole("ADMIN")
            .anyExchange().authenticated().and().build()
    }
}