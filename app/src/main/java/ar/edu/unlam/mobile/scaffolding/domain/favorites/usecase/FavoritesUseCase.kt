package ar.edu.unlam.mobile.scaffolding.domain.favorites.usecase

import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import kotlinx.coroutines.flow.Flow

interface FavoritesUseCase {
    suspend fun addToFavorites(
        name: String,
        avatarUrl: String,
    )

    suspend fun getFavorites(): Flow<List<FavoriteUser>>

    suspend fun deleteFavoriteUser(user: FavoriteUser)
}
