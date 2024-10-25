package ar.edu.unlam.mobile.scaffolding.domain.user.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface UserRepository {
    fun logOutUser(user: User)

    fun logInUser(
        name: String,
        password: String,
    ): User?

    fun createUser(user: User)
}
