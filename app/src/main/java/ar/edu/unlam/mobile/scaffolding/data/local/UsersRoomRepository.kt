package ar.edu.unlam.mobile.scaffolding.data.local

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.repository.LocalUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import asEntity
import asModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRoomRepository
    @Inject
    constructor(
        private val appDb: UsersDatabase,
    ) : LocalUserRepository {
        private val usersDao = appDb.usersDao()

        override suspend fun getUser(): Flow<List<User>> {
            Log.i("HOLA", "HOLA")
            return usersDao.getUsers().map {
                it.map { userEntity ->
                    userEntity.asModel()
                }
            }
        }

        override suspend fun addUserData(user: User) {
            usersDao.addUserData(user.asEntity())
        }

        override suspend fun deleteUserData() = usersDao.deleteUserData()
    }
