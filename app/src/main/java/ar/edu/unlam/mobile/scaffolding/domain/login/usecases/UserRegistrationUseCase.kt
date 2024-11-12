package ar.edu.unlam.mobile.scaffolding.domain.login.usecases

interface UserRegistrationUseCase {
    suspend fun register(
        email: String,
        name: String,
        password: String,
    ): Boolean
}
