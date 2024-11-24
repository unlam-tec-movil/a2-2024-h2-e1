package ar.edu.unlam.mobile.scaffolding.data.repository.tuit

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.SavedMessagesDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.LastMessageManager
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.UserDataManager
import ar.edu.unlam.mobile.scaffolding.data.network.api.services.TuitApiImplementation
import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TuitApiService
    @Inject
    constructor(
        val api: TuitApiImplementation,
        val lastMessageLocalData: LastMessageManager,
        private val userLocalData: UserDataManager,
        val localDb: SavedMessagesDatabaseInterface,
    ) : TuitRepositoryInterface {
        override suspend fun postTuit(tuit: String): ApiResponseMessage {
            try {
                val responseBody = api.postNotTweet(userLocalData.getLoginData().orEmpty(), tuit)
                lastMessageLocalData.deleteStoredMessage()
                return responseBody
            } catch (e: Exception) {
                Log.i("Error", "Error al postear tuit")
                return ApiResponseMessage("No autorizado", 0)
            }
        }

        override suspend fun addPostToLocalData(tuit: String) {
            try {
                lastMessageLocalData.saveLastMessage(tuit)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }

        override suspend fun getLastTuitFromLocalData(): String = lastMessageLocalData.getLastMessage().orEmpty()

        override suspend fun changeLikeStatus(
            likeStatus: Boolean,
            idTuit: Int,
        ): Boolean {
            val token = userLocalData.getLoginData()
            if (likeStatus) {
                if (token != null) {
                    api.unlikePost(idTuit, token)
                }
            } else {
                if (token != null) {
                    api.likePost(idTuit, token)
                }
            }
            return false
        }

        override suspend fun getFeed(page: Int): List<Tuit> {
            val token = userLocalData.getLoginData()
            token == null && return emptyList()
            return api.getFeed(1, token.toString())
        }

        override suspend fun getUserTuitsByEmail(name: String): List<Tuit> {
            val token = userLocalData.getLoginData()
            token == null && return emptyList()
            val tuits = api.getFeed(1, token.toString()).filter { it.author == name }
            return tuits
        }

        override suspend fun addToDabase(tuit: String) {
            localDb.storeDraftMessage(tuit)
        }

        override suspend fun getDraftFromDatabase(): Flow<List<SavedMessage>> = localDb.getDraftMessages()

        override suspend fun deleteFromDatabase(draft: SavedMessage) {
            localDb.deleteDraftMessage(draft)
        }
    }
