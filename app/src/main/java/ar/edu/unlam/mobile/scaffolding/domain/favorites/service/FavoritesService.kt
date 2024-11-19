package ar.edu.unlam.mobile.scaffolding.domain.favorites.service

import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.favorites.usecase.FavoritesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesService
    @Inject
    constructor(
        private val localDb: RoomDataBaseRepository,
    ) : FavoritesUseCase {
        override suspend fun addToFavorites(
            name: String,
            avatarUrl: String,
        ) {
            localDb.storeFavoriteUser(name, avatarUrl)
        }

        override suspend fun getFavorites(): Flow<List<FavoriteUser>> = localDb.getFavoritesUsers()

        override suspend fun deleteFavoriteUser(user: FavoriteUser) {
            localDb.deleteFavoriteUser(user)
        }
    }
