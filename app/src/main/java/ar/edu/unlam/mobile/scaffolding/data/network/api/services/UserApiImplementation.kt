package ar.edu.unlam.mobile.scaffolding.data.network.api.services

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.UpdateProfileBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.toDomain
import ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces.UserApiClient
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import javax.inject.Inject

class UserApiImplementation
    @Inject
    constructor(
        val api: UserApiClient,
    ) {
        suspend fun getProfile(token: String): User? {
            try {
                var profile = api.getProfile(token)
                return profile.toDomain()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                return null
            }
        }

        suspend fun updateProfile(
            token: String,
            updateProfileBody: UpdateProfileBodyDto,
        ): User? {
            try {
                val response = api.updateProfile(token, updateProfileBody)
                return response.toDomain()
            } catch (e: Exception) {
                Log.e("NotTwitterApiImpl", "Error al actualizar perfil: ${e.message}")
                return null
            }
        }
    }
