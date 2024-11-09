package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entity.LocalUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalUserDao {
    @Query("SELECT * FROM users")
    fun listUsers(): Flow<List<LocalUserEntity>>
       /* @Query("SELECT * FROM users WHERE email = :email")
        fun getUserByEmail(email: String): Flow<LocalUserEntity>*/
        @Insert
        suspend fun createUser(android: LocalUserEntity)
    }