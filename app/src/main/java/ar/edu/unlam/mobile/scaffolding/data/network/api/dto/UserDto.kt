package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import com.squareup.moshi.Json

class UserDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
)
