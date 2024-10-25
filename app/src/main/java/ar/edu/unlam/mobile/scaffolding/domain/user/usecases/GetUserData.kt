package ar.edu.unlam.mobile.scaffolding.domain.user.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import javax.inject.Inject

class GetUserData
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
        private val localDataBase: RoomDataBaseRepository,
    ) : GetUserService {
        override suspend fun getUserData(): User? {
            val token = localData.getLoginToken()
            if (token != null) {
                val user = api.getProfile(token.toString())
                if (user != null) localDataBase.createUser(user)
                user != null && return user
            }
            return null
        }
    }
