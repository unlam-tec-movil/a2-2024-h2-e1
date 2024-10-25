package ar.edu.unlam.mobile.scaffolding.domain.user.services

interface UserRegistrationService {
    suspend fun register(
        email: String,
        name: String,
        password: String,
    ): Boolean
}
