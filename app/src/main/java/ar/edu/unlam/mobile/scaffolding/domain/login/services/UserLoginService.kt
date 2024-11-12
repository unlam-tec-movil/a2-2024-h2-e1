package ar.edu.unlam.mobile.scaffolding.domain.login.services

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.login.usecases.UserLoginUseCase
import javax.inject.Inject

class UserLoginService
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
    ) : UserLoginUseCase {
        override suspend fun login(
            email: String,
            password: String,
        ): Boolean {
            val response = api.logUser(email = email, password = password) ?: return false
            localData.saveLoginToken(response.token)
            return true
        }
    }
