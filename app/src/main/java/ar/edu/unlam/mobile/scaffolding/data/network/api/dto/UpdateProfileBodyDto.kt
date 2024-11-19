package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import com.squareup.moshi.Json
import javax.inject.Inject

data class UpdateProfileBodyDto
    @Inject
    constructor(
        val name: String,
        @Json(name = "avatar_url")
        val avatarUrl: String,
        val password: String? = null, // Campo opcional si es necesario
    )
