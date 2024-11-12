package ar.edu.unlam.mobile.scaffolding.data.network.repository

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.network.api.NotTwitterApiClient
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.NewPostBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.RegisterBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.toDomain
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.TuitResponseDto
import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Like
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
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
                var registered =
                    api.registerUser(RegisterBodyDto(email = email, password = password, name = name))
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
                Log.e("Error_Posteo", e.message.orEmpty())
                return ApiResponseMessage(e.message.orEmpty(), 0)
            }
        }

        suspend fun getFeed(
            page: Int,
            token: String,
        ): List<Tuit> {
            try {
                val tuits = api.getFeed(page, token)
                if (tuits.isEmpty()) {
                    return emptyList()
                }
                return tuits.map { it.toDomain() }
            } catch (e: Exception) {
                Log.i("ERROR", e.message.orEmpty())
                return emptyList()
            }
        }

    suspend fun likePost(postId: Int, token: String): Tuit? {
        return try {
            val response = api.likePost(postId, token)
            val respuesta= response.toDomain()
            Log.i("resp", respuesta.toString())
            respuesta
        } catch (e: Exception) {
            Log.e("Error", e.message.orEmpty())
            return null
           // ApiResponseMessage(e.message.orEmpty(), 0)
        }
    }

    suspend fun unlikePost(postId: Int, token: String): Tuit? {
        return try {
            val response = api.unlikePost(postId, token)
            //response.toDomain()
            val respuesta= response.toDomain()
            Log.i("resp", respuesta.toString())
            respuesta
        } catch (e: Exception) {
            Log.e("Error", e.message.orEmpty())
            return null
        }
    }

    /*
    suspend fun unlikePost(postId: Int, token: String): ApiResponseMessage {
        return try {
            val response = api.unlikePost(postId, token)
            response.toDomain()
        } catch (e: Exception) {
            Log.e("Error", e.message.orEmpty())
            ApiResponseMessage(e.message.orEmpty(), 0)
        }
    }
     */



}





