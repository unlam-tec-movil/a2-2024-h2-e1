package ar.edu.unlam.mobile.scaffolding.domain.user.services

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.GetUserDataUseCase
import javax.inject.Inject

class GetUserService
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
        private val localDataBase: RoomDataBaseRepository,
    ) : GetUserDataUseCase {
        override suspend fun getUserData(): User? {
            val token = localData.getLoginToken()
            if (token != null) {
                val user = api.getProfile(token.toString())
                if (user != null && localDataBase.createUser(user)) {
                    return(user)
                }
            }
            return null
        }
    }
