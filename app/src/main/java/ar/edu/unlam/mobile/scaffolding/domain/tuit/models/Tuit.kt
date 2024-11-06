package ar.edu.unlam.mobile.scaffolding.domain.tuit.models

data class Tuit(
    val id: Int,
    val message: String,
    val parentId: Int,
    val author: String,
    val avatarUrl: String,
    val likes: Int,
    val liked: Boolean,
    val date: String,
)
