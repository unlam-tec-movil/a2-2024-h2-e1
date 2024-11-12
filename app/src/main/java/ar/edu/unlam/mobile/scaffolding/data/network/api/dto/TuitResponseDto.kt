package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import com.squareup.moshi.Json
import javax.inject.Inject

class TuitResponseDto
    @Inject
    constructor(
        @Json(name = "id")
        val id: Int,
        @Json(name = "message")
        val message: String,
        @Json(name = "parent_id")
        val parent_id: Int,
        @Json(name = "author")
        val author: String,
        @Json(name = "avatar_url")
        val avatar_url: String,
        @Json(name = "likes")
        val likes: Int,
        @Json(name = "liked")
        val liked: Boolean,
        @Json(name = "date")
        val date: String,
    )

fun TuitResponseDto.toDomain(): Tuit =
    Tuit(
        id = this.id,
        message = this.message,
        parent_id = this.parent_id,
        author = this.author,
        avatar_url = this.avatar_url,
        likes = this.likes,
        liked = this.liked,
        date = this.date,
    )
