package ar.edu.unlam.mobile.scaffolding.domain.login.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.login.services.UserRegistrationService
import javax.inject.Inject

class UserRegistration
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
    ) : UserRegistrationService {
        override suspend fun register(
            email: String,
            name: String,
            password: String,
        ): Boolean {
            val response = api.registerUser(email = email, name = name, password = password) ?: return false
            localData.saveLoginToken(response.token)
            return true
        }
    }
