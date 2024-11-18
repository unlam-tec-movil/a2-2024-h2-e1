package ar.edu.unlam.mobile.scaffolding.data.local.repository

import ar.edu.unlam.mobile.scaffolding.domain.repository.LocalUserDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseImplementation
    @Inject
    constructor(
        private val local: DataBaseRepository,
    ) : LocalUserDataBaseRepository {
        override suspend fun listUsers(): Flow<List<User>> = local.listUsers()

        override suspend fun createUsers(user: User) = local.createUser(user)

        override suspend fun storeDraftMessage(message: String) = local.storeDraftMessage(message)

        override fun getDraftMessages(): Flow<List<SavedMessage>> = local.getDraftMessages()

        override suspend fun deleteDraftMessage(draft: SavedMessage) = local.deleteDraftMessage(draft)
    }
