package ar.edu.unlam.mobile.scaffolding.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.SavedMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TuitDao {
    @Insert
    suspend fun storeMessage(message: SavedMessageEntity)

    @Query("SELECT * FROM messages")
    fun getMessages(): Flow<List<SavedMessageEntity>>

    @Query("DELETE from messages  where id= :id")
    suspend fun deleteMessage(id: Int)
}
