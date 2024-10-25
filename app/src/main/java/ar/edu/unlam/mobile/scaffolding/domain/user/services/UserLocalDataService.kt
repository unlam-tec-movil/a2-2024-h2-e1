package ar.edu.unlam.mobile.scaffolding.domain.user.services

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.LocalUserDataRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.AccessToUserLocalDataUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataService
    @Inject
    constructor(
        private val localUserDataRepository: LocalUserDataRepository,
    ) : AccessToUserLocalDataUseCase {
        override suspend fun getUser(): Flow<List<User>> = localUserDataRepository.getUser()

        override suspend fun addUserData(user: User) = localUserDataRepository.addUserData(user)

        override suspend fun deleteUserData() = localUserDataRepository.deleteUserData()
    }
