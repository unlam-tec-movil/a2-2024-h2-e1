package ar.edu.unlam.mobile.scaffolding.data.local.repository

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.LocalDataBase
import ar.edu.unlam.mobile.scaffolding.data.local.entity.FavoriteUserEntity
import ar.edu.unlam.mobile.scaffolding.data.local.entity.SavedMessageEntity
import ar.edu.unlam.mobile.scaffolding.data.local.entity.asEntity
import ar.edu.unlam.mobile.scaffolding.data.local.entity.asModel
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataBaseRepository
    @Inject
    constructor(
        private val appDb: LocalDataBase,
    ) : DataBaseRepository {
        // Todo, recibir el dao por constructor
        private val localUsersDao = appDb.localUserDao()
        private val tuitDao = appDb.tuitDao()
        private val favoriteUsersDao = appDb.favoriteUsersDao()

        override suspend fun listUsers(): Flow<List<User>> =
            localUsersDao.listUsers().map {
                it.map { localUserEntity ->
                    localUserEntity.asModel()
                }
            }

        override suspend fun createUser(user: User): Boolean {
            try {
                localUsersDao.createUser(user.asEntity())
                Log.i("RoomDataBaseRepository", "User created: $user")
                return true
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error creating user: $e")
                return false
            }
        }

        override suspend fun storeDraftMessage(message: String) {
            try {
                tuitDao.storeMessage(SavedMessageEntity(text = message))
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error saving Message: ${e.message}")
            }
        }

        override fun getDraftMessages(): Flow<List<SavedMessage>> {
            try {
                return tuitDao.getMessages().map {
                    it.map { localUserEntity ->
                        Log.i("RoomDataBaseRepository", "User created: $localUserEntity")
                        localUserEntity.asModel()
                    }
                }
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error getting message: ${e.message}")
                return flowOf(emptyList())
            }
        }

        override suspend fun deleteDraftMessage(draft: SavedMessage) {
            try {
                tuitDao.deleteMessage(draft.id)
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error deleting message: ${e.message}")
            }
        }

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
