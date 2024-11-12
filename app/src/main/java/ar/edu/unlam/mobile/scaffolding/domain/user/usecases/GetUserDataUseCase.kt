package ar.edu.unlam.mobile.scaffolding.domain.user.usecases

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface GetUserDataUseCase {
    suspend fun getUserData(): User?
}
