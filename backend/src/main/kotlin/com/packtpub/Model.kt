package com.packtpub

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "projects")
data class Project(
    val name: String,
    val url: String,
    val owner: String,
    val language: Language,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    val description: String? = null,

    @ElementCollection
    val tags: List<String> = listOf(),
    val license: String? = null
)

enum class Language {
    KOTLIN, JAVASCRIPT, JAVA
}

data class ProjectView(val name:String, val url:String, val owner:String)
@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubApiDTO(
    val description: String = "",
    val license: String? = null,
    @JsonProperty("topics")
    val tags:List<String> = listOf()
)