package ar.edu.unlam.mobile.scaffolding.domain.user.services

interface GetUserService {
    suspend fun getUserData(): Boolean
}
