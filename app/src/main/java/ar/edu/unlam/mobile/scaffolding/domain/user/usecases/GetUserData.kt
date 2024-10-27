package ar.edu.unlam.mobile.scaffolding.domain.user.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import javax.inject.Inject

class GetUserData
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
        private val localDataBase: RoomDataBaseRepository,
    ) : GetUserService {
        override suspend fun getUserData(): Boolean {
            val token = localData.getLoginToken()
            if (token != null) {
                val user = api.getProfile(token.toString())
                if (user != null) {
                    return(localDataBase.createUser(user))
                }
            }
            return false
        }
    }
