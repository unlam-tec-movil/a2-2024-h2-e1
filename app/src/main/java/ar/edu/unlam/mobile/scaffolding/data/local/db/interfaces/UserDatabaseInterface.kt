package ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow

interface UserDatabaseInterface {
    suspend fun listUsers(): Flow<List<User>>

    suspend fun createUsers(user: User): Boolean
}
