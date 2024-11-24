package ar.edu.unlam.mobile.scaffolding.data.repository.login

import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.UserDataManager
import ar.edu.unlam.mobile.scaffolding.data.network.api.services.LoginApiImplementation
import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import ar.edu.unlam.mobile.scaffolding.domain.login.repository.LoginApiInterface
import javax.inject.Inject

class LoginApiService
    @Inject
    constructor(
        private val loginApi: LoginApiImplementation,
        private val userLocalData: UserDataManager,
    ) : LoginApiInterface {
        override suspend fun logUser(
            email: String,
            password: String,
        ): LoggedUserToken? {
            val response = loginApi.logUser(email, password)
            if (response != null) {
                userLocalData.saveLoginData(response.token)
                return LoggedUserToken(token = response.token)
            }
            return null
        }

        override suspend fun registerUser(
            email: String,
            name: String,
            password: String,
        ): LoggedUserToken? {
            val response = loginApi.registerUser(email, name, password)
            if (response != null) {
                userLocalData.saveLoginData(response.token)
                return LoggedUserToken(token = response.token)
            }
            return null
        }
    }
