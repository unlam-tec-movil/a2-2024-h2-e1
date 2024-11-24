package ar.edu.unlam.mobile.scaffolding.domain.login.services

import ar.edu.unlam.mobile.scaffolding.domain.login.repository.LoginApiInterface
import ar.edu.unlam.mobile.scaffolding.domain.login.usecases.UserRegistrationUseCase
import javax.inject.Inject

class UserRegistrationService
    @Inject
    constructor(
        private val api: LoginApiInterface,
    ) : UserRegistrationUseCase {
        override suspend fun register(
            email: String,
            name: String,
            password: String,
        ): Boolean = api.registerUser(email, name, password) != null
    }
