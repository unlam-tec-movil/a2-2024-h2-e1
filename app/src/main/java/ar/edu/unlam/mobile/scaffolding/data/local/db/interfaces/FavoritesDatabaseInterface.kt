package ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces

import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import kotlinx.coroutines.flow.Flow

interface FavoritesDatabaseInterface {
    suspend fun storeFavoriteUser(
        name: String,
        avatarUrl: String,
    )

    fun getFavoritesUsers(): Flow<List<FavoriteUser>>

    suspend fun deleteFavoriteUser(user: FavoriteUser)
}
