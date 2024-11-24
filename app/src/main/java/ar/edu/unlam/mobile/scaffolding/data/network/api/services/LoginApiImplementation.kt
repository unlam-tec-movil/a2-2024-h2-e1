package ar.edu.unlam.mobile.scaffolding.data.network.api.services

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.RegisterBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.toDomain
import ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces.LoginApiClient
import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import javax.inject.Inject

class LoginApiImplementation
    @Inject
    constructor(
        private val api: LoginApiClient,
    ) {
        suspend fun logUser(
            email: String,
            password: String,
        ): LoggedUserToken? {
            try {
                var logged = api.logInUser(LoginBodyDto(email = email, password = password))
                return logged.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }

        suspend fun registerUser(
            email: String,
            name: String,
            password: String,
        ): LoggedUserToken? {
            try {
                var registered =
                    api.registerUser(RegisterBodyDto(email = email, password = password, name = name))
                return registered.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }
    }
