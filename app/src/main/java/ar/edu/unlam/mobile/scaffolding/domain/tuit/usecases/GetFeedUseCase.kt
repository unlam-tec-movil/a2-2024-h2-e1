package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

interface GetFeedUseCase {
    suspend fun getFeed(): List<Tuit>?

    suspend fun getUserTuitsByEmail(email: String): List<Tuit>?
}
