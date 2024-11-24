package ar.edu.unlam.mobile.scaffolding.domain.user.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface UserRepositoryInterface {
    suspend fun getUserData(): User?

    suspend fun updateProfile(
        name: String,
        avatarUrl: String,
    ): User?
}
