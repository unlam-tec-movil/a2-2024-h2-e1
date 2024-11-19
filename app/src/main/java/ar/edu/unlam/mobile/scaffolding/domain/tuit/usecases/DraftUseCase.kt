package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import kotlinx.coroutines.flow.Flow

interface DraftUseCase {
    suspend fun addToDabase(tuit: String)

    suspend fun getDraftFromDatabase(): Flow<List<SavedMessage>>

    suspend fun deleteFromDatabase(draft: SavedMessage)
}
