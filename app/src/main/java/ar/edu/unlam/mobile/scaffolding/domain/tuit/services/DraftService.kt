package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.DraftUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DraftService
    @Inject
    constructor(
        private val localDb: RoomDataBaseRepository,
    ) : DraftUseCase {
        override suspend fun addToDabase(tuit: String) {
            localDb.storeDraftMessage(tuit)
        }

        override suspend fun getDraftFromDatabase(): Flow<List<SavedMessage>> = localDb.getDraftMessages()

        override suspend fun deleteFromDatabase(draft: SavedMessage) {
            localDb.deleteDraftMessage(draft)
        }
    }
