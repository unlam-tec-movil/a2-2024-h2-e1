package ar.edu.unlam.mobile.scaffolding.domain.user.services

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface GetUserService {
    suspend fun getUserData(): User?
}
