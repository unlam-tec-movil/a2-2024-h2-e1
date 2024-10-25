package ar.edu.unlam.mobile.scaffolding.data.local.repository

import ar.edu.unlam.mobile.scaffolding.data.local.LocalDataBase
import ar.edu.unlam.mobile.scaffolding.data.local.entity.asEntity
import ar.edu.unlam.mobile.scaffolding.data.local.entity.asModel
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataBaseRepository
    @Inject
    constructor(
        private val appDb: LocalDataBase,
    ) : DataBaseRepository {
        // Todo, recibir el dao por constructor
        private val localUsersDao = appDb.localUserDao()

        override suspend fun listUsers(): Flow<List<User>> =
            localUsersDao.listUsers().map {
                it.map { localUserEntity ->
                    localUserEntity.asModel()
                }
            }

        override suspend fun createUser(user: User) {
            localUsersDao.createUser(user.asEntity())
        }
    }
