package ar.edu.unlam.mobile.scaffolding.data.local.db.services

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.db.LocalDataBase
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.SavedMessageEntity
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.asModel
import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.SavedMessagesDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SavedMessagesDatabaseImplementation
    @Inject
    constructor(
        private val appDb: LocalDataBase,
    ) : SavedMessagesDatabaseInterface {
        val tuitDao = appDb.tuitDao()

        override suspend fun storeDraftMessage(message: String) {
            try {
                tuitDao.storeMessage(SavedMessageEntity(text = message))
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error saving Message: ${e.message}")
            }
        }

        override fun getDraftMessages(): Flow<List<SavedMessage>> {
            try {
                return tuitDao.getMessages().map {
                    it.map { localUserEntity ->
                        Log.i("RoomDataBaseRepository", "User created: $localUserEntity")
                        localUserEntity.asModel()
                    }
                }
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error getting message: ${e.message}")
                return flowOf(emptyList())
            }
        }

        override suspend fun deleteDraftMessage(draft: SavedMessage) {
            try {
                tuitDao.deleteMessage(draft.id)
            } catch (e: Exception) {
                Log.i("RoomDataBaseRepository", "Error deleting message: ${e.message}")
            }
        }
    }
