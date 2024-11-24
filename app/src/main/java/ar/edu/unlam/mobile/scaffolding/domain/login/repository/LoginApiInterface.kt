package ar.edu.unlam.mobile.scaffolding.domain.login.repository

import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken

interface LoginApiInterface {
    suspend fun logUser(
        email: String,
        password: String,
    ): LoggedUserToken?

    suspend fun registerUser(
        email: String,
        name: String,
        password: String,
    ): LoggedUserToken?
}
