package ar.edu.unlam.mobile.scaffolding.data.local.db.services

import ar.edu.unlam.mobile.scaffolding.data.local.db.LocalDataBase
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.FavoriteUserEntity
import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.FavoritesDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesDatabaseImplementation
    @Inject
    constructor(
        private val appDb: LocalDataBase,
    ) : FavoritesDatabaseInterface {
        val favoriteUsersDao = appDb.favoriteUsersDao()

        override suspend fun storeFavoriteUser(
            name: String,
            avatarUrl: String,
        ) {
            favoriteUsersDao.storeUser(FavoriteUserEntity(name = name, avatar_url = avatarUrl))
        }

        override fun getFavoritesUsers(): Flow<List<FavoriteUser>> = favoriteUsersDao.getFavoriteUsers()

        override suspend fun deleteFavoriteUser(user: FavoriteUser) {
            favoriteUsersDao.deleteFavoriteUser(user.id)
        }
    }
