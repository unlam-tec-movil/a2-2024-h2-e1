package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.login.models.LoggedUserToken
import com.squareup.moshi.Json
import javax.inject.Inject

data class TokenResponseDto
    @Inject
    constructor(
        @Json(name = "token")
        val token: String,
    )

fun TokenResponseDto.toDomain(): LoggedUserToken? {
    if (this.token.isEmpty()) {
        return null
    }

    return LoggedUserToken(
        token = this.token,
    )
}
