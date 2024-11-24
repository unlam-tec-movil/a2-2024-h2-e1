package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.PostNewTuitUseCase
import javax.inject.Inject

class PostNewTuitService
    @Inject
    constructor(
        private val api: TuitRepositoryInterface,
    ) : PostNewTuitUseCase {
        override suspend fun postTuit(tuit: String): ApiResponseMessage = api.postTuit(tuit)

        override suspend fun addPostToLocalData(tuit: String) = api.addPostToLocalData(tuit)

        override suspend fun getLastTuitFromLocalData(): String = api.getLastTuitFromLocalData()
    }
