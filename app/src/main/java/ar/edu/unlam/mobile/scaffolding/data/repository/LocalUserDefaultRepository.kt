package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.LocalUserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDefaultRepository
    @Inject
    constructor(
        private val local: LocalUserRepository,
    ) : LocalUserDataRepository {
        override suspend fun getUser(): Flow<List<User>> = local.getUser()

        override suspend fun addUserData(user: User) = local.addUserData(user)

        override suspend fun deleteUserData() = local.deleteUserData()
    }
