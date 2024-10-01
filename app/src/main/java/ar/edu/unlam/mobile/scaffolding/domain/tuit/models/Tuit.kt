package ar.edu.unlam.mobile.scaffolding.domain.tuit.models

data class Tuit(
    val id: Int,
    val authorName: String,
    val content: String,
    val avatar: String,
    val likes: Int,
    val liked: Boolean,
    val replies: Int,
    val reply: (id: Int) -> Void,
)