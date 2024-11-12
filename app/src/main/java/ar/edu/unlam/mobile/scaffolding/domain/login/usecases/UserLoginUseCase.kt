package ar.edu.unlam.mobile.scaffolding.domain.login.usecases

interface UserLoginUseCase {
    suspend fun login(
        email: String,
        password: String,
    ): Boolean
}
