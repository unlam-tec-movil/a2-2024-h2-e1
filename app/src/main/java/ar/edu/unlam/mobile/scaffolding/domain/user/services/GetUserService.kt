package ar.edu.unlam.mobile.scaffolding.domain.user.services

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.GetUserDataUseCase
import javax.inject.Inject

class GetUserService
    @Inject
    constructor(
        private val api: UserRepositoryInterface,
    ) : GetUserDataUseCase {
        override suspend fun getUserData(): User? = api.getUserData()

        override suspend fun updateProfile(
            name: String,
            avatarUrl: String,
        ): User? = api.updateProfile(name, avatarUrl)
    }
