package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import com.squareup.moshi.Json
import javax.inject.Inject

data class LoginResponseDto
    @Inject
    constructor(
        @Json(name = "name")
        val name: String,
        @Json(name = "email")
        val email: String,
        @Json(name = "token")
        val token: String,
    )

fun LoginResponseDto.toDomain(): LoggedUserToken? {
    if (this.token.isNullOrEmpty()) {
        return null
    }

    return LoggedUserToken(
        email = this.email,
        token = this.token,
    )
}
