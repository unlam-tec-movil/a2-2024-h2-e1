package ar.edu.unlam.mobile.scaffolding.domain.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow

interface LocalUserDataBaseRepository {
    suspend fun listUsers(): Flow<List<User>>

    suspend fun createUsers(user: User): Boolean

    suspend fun storeDraftMessage(message: String)

    fun getDraftMessages(): Flow<List<SavedMessage>>

    suspend fun deleteDraftMessage(draft: SavedMessage)
}
