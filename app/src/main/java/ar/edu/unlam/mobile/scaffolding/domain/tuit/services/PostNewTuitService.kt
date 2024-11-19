package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeedUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.PostNewTuitUseCase
import javax.inject.Inject

class PostNewTuitService
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localDb: RoomDataBaseRepository,
        private val localData: LocalDataRepository,
        private val getFeed: GetFeedUseCase,
    ) : PostNewTuitUseCase {
        override suspend fun postTuit(tuit: String): ApiResponseMessage {
            val token = localData.getLoginToken()
            if (token != null) {
                val responseBody = api.postNotTweet(token.toString(), tuit)
                getFeed.getFeed()
                localData.deleteStoredMessage()
                return responseBody
            }

            return ApiResponseMessage("No autorizado", 0)
        }

        override suspend fun addPostToLocalData(tuit: String) {
            try {
                localData.storeLastMessage(tuit)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }

        override suspend fun getLastTuitFromLocalData(): String = localData.getLastMessage()
    }
