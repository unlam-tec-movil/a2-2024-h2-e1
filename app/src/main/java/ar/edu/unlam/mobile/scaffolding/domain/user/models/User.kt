package ar.edu.unlam.mobile.scaffolding.domain.user.models

data class User(
    val id: UInt?,
    val avatarUrl: String,
    val email: String,
    val name: String,
    val password: String,
    // val logged: Boolean,
)
