package ar.edu.unlam.mobile.scaffolding.domain.user.usecases

import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.services.UserRegistrationService
import javax.inject.Inject

class UserRegistration
    @Inject
    constructor(
        private val loginRepository: ApiRepository,
    ) : UserRegistrationService {
        override suspend fun register(
            email: String,
            name: String,
            password: String,
        ): Boolean {
            val user = loginRepository.registerUser(email, name, password)
            return user != null
        }
    }
