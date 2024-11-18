package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.LikeUseCase
import javax.inject.Inject

class LikeService
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
    ) : LikeUseCase {
        override suspend fun changeLikeStatus(
            likeStatus: Boolean,
            idTuit: Int,
        ): Boolean {
            val token = localData.getLoginToken()
            if (likeStatus) {
                if (token != null) {
                    api.unlikePost(idTuit, token)
                }
            } else {
                if (token != null) {
                    api.likePost(idTuit, token)
                }
            }
            return false
        }
    }
