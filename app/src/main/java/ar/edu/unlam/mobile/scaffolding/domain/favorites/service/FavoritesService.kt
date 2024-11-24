package ar.edu.unlam.mobile.scaffolding.domain.favorites.service

import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.favorites.repository.FavoritesRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.favorites.usecase.FavoritesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesService
    @Inject
    constructor(
        private val api: FavoritesRepositoryInterface,
    ) : FavoritesUseCase {
        override suspend fun addToFavorites(
            name: String,
            avatarUrl: String,
        ) = api.addToFavorites(name, avatarUrl)

        override suspend fun getFavorites(): Flow<List<FavoriteUser>> = api.getFavorites()

        override suspend fun deleteFavoriteUser(user: FavoriteUser) = api.deleteFavoriteUser(user)
    }
