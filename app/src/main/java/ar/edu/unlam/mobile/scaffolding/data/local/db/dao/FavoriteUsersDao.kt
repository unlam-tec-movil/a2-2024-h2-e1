package ar.edu.unlam.mobile.scaffolding.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.FavoriteUserEntity
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteUsersDao {
    @Insert
    suspend fun storeUser(data: FavoriteUserEntity)

    @Query("SELECT * FROM favorites")
    fun getFavoriteUsers(): Flow<List<FavoriteUser>>

    @Query("DELETE from messages  where id= :id")
    suspend fun deleteFavoriteUser(id: Int)
}
