package ar.edu.unlam.mobile.scaffolding.domain.login.services

import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.UpdateProfileBodyDto
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface UserLoginService {
    suspend fun login(
        email: String,
        password: String,
    ): Boolean
    suspend fun updateProfile(token: String, updateProfileBody: UpdateProfileBodyDto): User?
}
