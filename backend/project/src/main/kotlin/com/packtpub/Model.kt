package com.packtpub

//import com.fasterxml.jackson.annotation.JsonInclude
//import org.hibernate.validator.constraints.URL
//import javax.validation.constraints.Size

data class Project(
    val name:String,
    val url:String,
    val owner:String,
    val language: Language,
    val tags:List<String>,
    val license: String?
)

//data class ProjectDTO(
//    @get: Size(min = 2)
//    val name:String,
//
//    @get: URL
//    val url: String,
//
//    @get: Size(min = 2)
//    val owner:String,
//    val language: Language
//): Validatable()

//@JsonInclude(JsonInclude.Include.NON_NULL)
open class Validatable(
    var fieldErrors: List<FieldErrorDTO>? = null,
    var genericError: String? = null
)

enum class Language(val label: String) {
       KOTLIN("Kotlin"), JAVASCRIPT("JS"), JAVA("Java")
}

data class FieldErrorDTO(val field:String, val message:String)