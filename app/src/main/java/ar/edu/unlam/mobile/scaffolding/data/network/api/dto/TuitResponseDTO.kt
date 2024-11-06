package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import com.squareup.moshi.Json
import javax.inject.Inject

data class TuitResponseDTO
    @Inject
    constructor(
        @Json(name = "id")
        val id: Int,
        @Json(name = "message")
        val message: String,
        @Json(name = "parent_id")
        val parentId: Int,
        @Json(name = "author")
        val author: String,
        @Json(name = "avatar_url")
        val avatarUrl: String,
        @Json(name = "likes")
        val likes: Int,
        @Json(name = "liked")
        val liked: Boolean,
        @Json(name = "date")
        val date: String,
    )

fun TuitResponseDTO.toDomain(): Tuit =
    Tuit(
        id = this.id,
        message = this.message,
        parentId = this.parentId,
        author = this.author,
        avatarUrl = this.avatarUrl,
        likes = this.likes,
        liked = this.liked,
        date = this.date,
    )
