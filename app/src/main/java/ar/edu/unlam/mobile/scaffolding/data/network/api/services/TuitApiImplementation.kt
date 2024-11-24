package ar.edu.unlam.mobile.scaffolding.data.network.api.services

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.NewPostBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.toDomain
import ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces.TuitApiClient
import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import javax.inject.Inject

class TuitApiImplementation
    @Inject
    constructor(
        private val api: TuitApiClient,
    ) {
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
                Log.i("pruebaGetFeed", tuits.toString())
                if (tuits.isEmpty()) {
                    return emptyList()
                }
                return tuits.map { it.toDomain() }
            } catch (e: Exception) {
                Log.i("ERROR", e.message.orEmpty())
                return emptyList()
            }
        }

        suspend fun likePost(
            postId: Int,
            token: String,
        ): Tuit? {
            return try {
                val response = api.likePost(postId, token)
                val respuesta = response.toDomain()
                Log.i("resp", respuesta.toString())
                respuesta
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
                // ApiResponseMessage(e.message.orEmpty(), 0)
            }
        }

        suspend fun unlikePost(
            postId: Int,
            token: String,
        ): Tuit? {
            return try {
                val response = api.unlikePost(postId, token)
                // response.toDomain()
                val respuesta = response.toDomain()
                Log.i("resp", respuesta.toString())
                respuesta
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }
    }
