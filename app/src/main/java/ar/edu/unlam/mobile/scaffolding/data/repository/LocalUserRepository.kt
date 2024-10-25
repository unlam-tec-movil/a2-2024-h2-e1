package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {
    suspend fun getUser(): Flow<List<User>>

    suspend fun addUserData(user: User)

    suspend fun deleteUserData()
}
