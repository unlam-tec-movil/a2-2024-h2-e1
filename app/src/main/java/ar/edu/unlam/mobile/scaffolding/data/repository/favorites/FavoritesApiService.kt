package ar.edu.unlam.mobile.scaffolding.data.repository.favorites

import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.FavoritesDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.favorites.repository.FavoritesRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesApiService
    @Inject
    constructor(
        private val localDb: FavoritesDatabaseInterface,
    ) : FavoritesRepositoryInterface {
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
