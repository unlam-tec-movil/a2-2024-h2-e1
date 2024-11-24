package ar.edu.unlam.mobile.scaffolding.domain.login.services

import ar.edu.unlam.mobile.scaffolding.domain.login.repository.LoginApiInterface
import ar.edu.unlam.mobile.scaffolding.domain.login.usecases.UserLoginUseCase
import javax.inject.Inject

class UserLoginService
    @Inject
    constructor(
        private val api: LoginApiInterface,
    ) : UserLoginUseCase {
        override suspend fun login(
            email: String,
            password: String,
        ): Boolean = api.logUser(email = email, password = password) != null
    }
