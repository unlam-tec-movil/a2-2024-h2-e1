package ar.edu.unlam.mobile.scaffolding.data.repository.user

import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.UserDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.UserDataManager
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.UpdateProfileBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.services.UserApiImplementation
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepositoryInterface
import javax.inject.Inject

class UserApiService
    @Inject
    constructor(
        val api: UserApiImplementation,
        val userLocalData: UserDataManager,
        private val localDataBase: UserDatabaseInterface,
    ) : UserRepositoryInterface {
        override suspend fun getUserData(): User? {
            val token = userLocalData.getLoginData()
            if (token != null) {
                val user = api.getProfile(token.toString())
                if (user != null && localDataBase.createUsers(user)) {
                    return(user)
                }
            }
            return null
        }

        override suspend fun updateProfile(
            name: String,
            avatarUrl: String,
        ): User? {
            val token = userLocalData.getLoginData()
            return if (token != null) {
                api.updateProfile(token, UpdateProfileBodyDto(name, avatarUrl))
            } else {
                null
            }
        }
    }
