package ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import kotlinx.coroutines.flow.Flow

interface SavedMessagesDatabaseInterface {
    suspend fun storeDraftMessage(message: String)

    fun getDraftMessages(): Flow<List<SavedMessage>>

    suspend fun deleteDraftMessage(draft: SavedMessage)
}
