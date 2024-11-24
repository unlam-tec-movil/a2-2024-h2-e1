package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepositoryInterface {
    suspend fun postTuit(tuit: String): ApiResponseMessage

    suspend fun addPostToLocalData(tuit: String)

    suspend fun getLastTuitFromLocalData(): String

    suspend fun changeLikeStatus(
        likeStatus: Boolean,
        idTuit: Int,
    ): Boolean

    suspend fun getFeed(page: Int): List<Tuit>

    suspend fun getUserTuitsByEmail(name: String): List<Tuit>

    suspend fun addToDabase(tuit: String)

    suspend fun getDraftFromDatabase(): Flow<List<SavedMessage>>

    suspend fun deleteFromDatabase(draft: SavedMessage)
}
