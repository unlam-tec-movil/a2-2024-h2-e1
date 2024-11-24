package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.LikeUseCase
import javax.inject.Inject

class LikeService
    @Inject
    constructor(
        private val api: TuitRepositoryInterface,
    ) : LikeUseCase {
        override suspend fun changeLikeStatus(
            likeState: Boolean,
            idTuit: Int,
        ): Boolean = api.changeLikeStatus(likeState, idTuit)
    }
