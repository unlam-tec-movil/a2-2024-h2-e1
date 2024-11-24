package ar.edu.unlam.mobile.scaffolding.data.local.db.services

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.db.LocalDataBase
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.asEntity
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.asModel
import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.UserDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDatabaseImplementation
    @Inject
    constructor(
        private val appDb: LocalDataBase,
    ) : UserDatabaseInterface {
        private val localUsersDao = appDb.localUserDao()

        override suspend fun listUsers(): Flow<List<User>> =
            localUsersDao.listUsers().map {
                it.map { localUserEntity ->
                    localUserEntity.asModel()
                }
            }

        override suspend fun createUsers(user: User): Boolean {
            try {
                localUsersDao.createUser(user.asEntity())
                Log.i("RoomDataBaseRepository", "User created: $user")
                return true
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error creating user: $e")
                return false
            }
        }
    }
