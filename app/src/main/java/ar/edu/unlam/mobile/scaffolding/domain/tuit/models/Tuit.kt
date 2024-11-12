package ar.edu.unlam.mobile.scaffolding.domain.tuit.models

data class Tuit(
    val id: Int,
    val message: String,
    val parent_id: Int,
    val author: String,
    val avatar_url: String,
    var likes: Long,
    var liked: Boolean,
    val date: String,
)
