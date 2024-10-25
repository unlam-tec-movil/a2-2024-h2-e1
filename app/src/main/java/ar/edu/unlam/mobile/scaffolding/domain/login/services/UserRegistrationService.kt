package ar.edu.unlam.mobile.scaffolding.domain.login.services

interface UserRegistrationService {
    suspend fun register(
        email: String,
        name: String,
        password: String,
    ): Boolean
}
