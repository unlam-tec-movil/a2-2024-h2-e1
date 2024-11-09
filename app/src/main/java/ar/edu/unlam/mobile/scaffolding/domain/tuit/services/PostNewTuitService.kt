package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.models.ApiResponseMessage

interface PostNewTuitService {
    suspend fun postTuit(tuit: String): ApiResponseMessage

    suspend fun addPostToLocalData(tuit: String): Unit

    suspend fun getLastTuitFromLocalData(): String

    suspend fun likePost(postId: Int): ApiResponseMessage

    suspend fun unlikePost(postId: Int): ApiResponseMessage
}
