package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Like
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import javax.inject.Inject

class LikeDto
@Inject
constructor(
    val postId: Int,
    val token: String
)
fun LikeDto.toDomain(): Like =
    Like(
        postId = this.postId,
        token = this.token
    )