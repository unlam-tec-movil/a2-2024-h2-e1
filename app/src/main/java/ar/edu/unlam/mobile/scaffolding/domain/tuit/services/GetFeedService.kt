package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository

interface GetFeedService {
    suspend fun getFeed(page: Int): List<Tuit>?

    suspend fun getUserTuitsByEmail(email: String): List<Tuit>?



}
