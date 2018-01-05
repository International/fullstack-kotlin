package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UserDetailsRepositoryAuthenticationManager
import org.springframework.security.config.web.server.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsRepository
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

fun BeanDefinitionDsl.securityBeans(paths: HttpSecurity.AuthorizeExchangeBuilder.(SecurityService)->HttpSecurity.AuthorizeExchangeBuilder) {
    bean<UserRepository>()

    bean<UserService> {
        UserServiceImpl(ref())
    }

    bean<SecurityService> {
        SecuritServiceImpl()
    }

    bean {
        UserDetailsRepository { username ->
            ref<UserService>().getUserByUsername(username)
                ?.toUserDetails()
                ?.toMono()
                ?: Mono.empty()
        }
    }

    bean<SecurityWebFilterChain>(){
        HttpSecurity.http().authorizeExchange()
            .paths(ref())
//            .pathMatchers(HttpMethod.GET, "/api/projects/**").permitAll()
//            .pathMatchers(HttpMethod.GET, "/projects/**").hasRole("ADMIN")
//            .pathMatchers(HttpMethod.POST, "/api/projects/**").hasRole("ADMIN")
            .anyExchange().authenticated()
            .and()
            .authenticationManager(UserDetailsRepositoryAuthenticationManager(ref()))
            .formLogin()
            .and()
            .build()
    }}