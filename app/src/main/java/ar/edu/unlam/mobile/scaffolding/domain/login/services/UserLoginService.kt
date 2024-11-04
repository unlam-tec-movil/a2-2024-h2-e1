package ar.edu.unlam.mobile.scaffolding.domain.login.services

interface UserLoginService {
    suspend fun login(
        email: String,
        password: String,
    ): Boolean
}
