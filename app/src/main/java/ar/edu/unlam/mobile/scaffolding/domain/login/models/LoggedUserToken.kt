package ar.edu.unlam.mobile.scaffolding.domain.login.models

data class LoggedUserToken(
    val email: String,
    val token: String,
)
