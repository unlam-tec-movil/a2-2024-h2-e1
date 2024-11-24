package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.DraftUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DraftService
    @Inject
    constructor(
        private val api: TuitRepositoryInterface,
    ) : DraftUseCase {
        override suspend fun addToDabase(tuit: String) = api.addToDabase(tuit)

        override suspend fun getDraftFromDatabase(): Flow<List<SavedMessage>> = api.getDraftFromDatabase()

        override suspend fun deleteFromDatabase(draft: SavedMessage) = api.deleteFromDatabase(draft)
    }
