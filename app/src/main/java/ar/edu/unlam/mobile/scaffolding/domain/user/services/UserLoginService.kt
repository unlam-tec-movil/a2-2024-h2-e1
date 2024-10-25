package ar.edu.unlam.mobile.scaffolding.domain.user.services

interface UserLoginService {
    suspend fun login(
        email: String,
        password: String,
    ): Boolean
}
