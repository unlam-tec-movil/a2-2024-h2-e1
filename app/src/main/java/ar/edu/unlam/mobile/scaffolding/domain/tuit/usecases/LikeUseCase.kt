package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

interface LikeUseCase {
    suspend fun changeLikeStatus(
        likeState: Boolean,
        idTuit: Int,
    ): Boolean
}
