package ar.edu.unlam.mobile.scaffolding.data.local.repository

import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    suspend fun listUsers(): Flow<List<User>>

    suspend fun createUser(user: User): Boolean

    suspend fun storeDraftMessage(message: String)

    fun getDraftMessages(): Flow<List<SavedMessage>>

    suspend fun deleteDraftMessage(draft: SavedMessage)

    suspend fun storeFavoriteUser(
        name: String,
        avatarUrl: String,
    )

    fun getFavoritesUsers(): Flow<List<FavoriteUser>>

    suspend fun deleteFavoriteUser(user: FavoriteUser)
}
