package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import com.squareup.moshi.Json

data class PostDto(
    @Json(name = "id")
    val id: String,
)
