package ar.edu.unlam.mobile.scaffolding.data.network.repository

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.network.api.NotTwitterApiClient
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.NewPostBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.RegisterBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.toDomain
import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
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
                var registered = api.registerUser(RegisterBodyDto(email = email, password = password, name = name))
                return registered.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }

        suspend fun getProfile(token: String): User? {
            try {
                var profile = api.getProfile(token)
                return profile.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }

        suspend fun postNotTweet(
            token: String,
            message: String,
        ): ApiResponseMessage {
            try {
                val response = api.postNotTweet(token, NewPostBodyDto(message))
                return response.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return ApiResponseMessage(e.message.orEmpty(), 0)
            }
        }
    }
