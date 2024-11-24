package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeedUseCase
import javax.inject.Inject

class GetFeedService
    @Inject
    constructor(
        private val api: TuitRepositoryInterface,
    ) : GetFeedUseCase {
        override suspend fun getFeed(): List<Tuit> = api.getFeed(1)

        override suspend fun getUserTuitsByEmail(name: String): List<Tuit> = api.getUserTuitsByEmail(name)
    }
