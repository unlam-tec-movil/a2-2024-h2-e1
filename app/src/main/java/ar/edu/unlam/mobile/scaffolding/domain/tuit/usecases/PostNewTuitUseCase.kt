package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage

interface PostNewTuitUseCase {
    suspend fun postTuit(tuit: String): ApiResponseMessage

    suspend fun addPostToLocalData(tuit: String): Unit

    suspend fun getLastTuitFromLocalData(): String
}
