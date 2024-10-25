package ar.edu.unlam.mobile.scaffolding.domain.user.usecases

import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.services.UserLoginService
import javax.inject.Inject

class UserLogin
    @Inject
    constructor(
        private val loginRepository: ApiRepository,
    ) : UserLoginService {
        override suspend fun login(
            email: String,
            password: String,
        ): Boolean {
            val user = loginRepository.logUser(email, password)

            return user != null
        }
    }
