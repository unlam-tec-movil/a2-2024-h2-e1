package ar.edu.unlam.mobile.scaffolding.domain.user.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow

interface LocalUserDataRepository {
    suspend fun getUser(): Flow<List<User>>

    suspend fun addUserData(user: User)

    suspend fun deleteUserData()
}
