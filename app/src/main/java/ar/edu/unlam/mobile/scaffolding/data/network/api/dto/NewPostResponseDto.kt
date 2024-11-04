package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import com.squareup.moshi.Json
import javax.inject.Inject

data class NewPostResponseDto
    @Inject
    constructor(
        @Json(name = "message")
        val message: String,
    )

fun NewPostResponseDto.toDomain(): ApiResponseMessage {
    if (this.message.isEmpty()) {
        return ApiResponseMessage(
            status = 0,
            message = "Algo ha salido mal con tu petición",
        )
    }
    return ApiResponseMessage(
        status = 1,
        message = this.message,
    )
}
