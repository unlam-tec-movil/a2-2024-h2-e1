package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import com.squareup.moshi.Json

data class ProfileResponseDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "avatar_url")
    val avatar_url: String,
    @Json(name = "email")
    val email: String,
)

fun ProfileResponseDto.toDomain(): User? {
    if (this.name.isEmpty()) {
        return null
    }

    return User(
        avatar_url = this.avatar_url,
        email = this.email,
        name = this.name,
    )
}
