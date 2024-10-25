package ar.edu.unlam.mobile.scaffolding.data.local

import UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUsers(): Flow<List<UserEntity>>

    @Insert
    suspend fun addUserData(userEntity: UserEntity)

    @Query("DELETE  FROM users")
    suspend fun deleteUserData()
}
