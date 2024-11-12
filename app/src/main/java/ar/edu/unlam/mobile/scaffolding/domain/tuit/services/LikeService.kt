package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

interface LikeService {
    suspend fun changeLikeStatus(likeState: Boolean, idTuit: Int): Boolean
}