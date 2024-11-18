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

    @Insert
    suspend fun createUser(android: LocalUserEntity)

    @Query("UPDATE users SET name = :name, avatar_url = :avatar where id = :id")
    fun updateUser(
        name: String,
        avatar: String,
        id: Int,
    )
}
