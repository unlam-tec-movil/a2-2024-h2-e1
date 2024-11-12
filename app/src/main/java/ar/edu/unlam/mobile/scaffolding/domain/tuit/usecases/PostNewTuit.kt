package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.PostNewTuitService
import javax.inject.Inject

class PostNewTuit
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
        private val getFeed: GetFeedService,
    ) : PostNewTuitService {
        override suspend fun postTuit(tuit: String): ApiResponseMessage {
            val token = localData.getLoginToken()
            if (token != null) {
                val responseBody = api.postNotTweet(token.toString(), tuit)
                getFeed.getFeed()
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
