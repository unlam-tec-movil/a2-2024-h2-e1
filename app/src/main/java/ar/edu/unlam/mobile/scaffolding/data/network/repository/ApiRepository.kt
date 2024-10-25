package ar.edu.unlam.mobile.scaffolding.data.network.repository

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.network.api.NotTwitterApiClient
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.RegisterBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.toDomain
import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import javax.inject.Inject

class ApiRepository
    @Inject
    constructor(
        private val api: NotTwitterApiClient,
    ) {
        suspend fun logUser(
            email: String,
            password: String,
        ): LoggedUserToken? {
            try {
                var logged = api.logInUser(LoginBodyDto(email, password))
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
                var registered = api.registerUser(RegisterBodyDto(email, name, password))
                return registered.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }
    }
