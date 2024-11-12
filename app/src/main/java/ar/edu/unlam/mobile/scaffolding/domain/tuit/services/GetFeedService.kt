package ar.edu.unlam.mobile.scaffolding.domain.tuit.services

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeedUseCase
import javax.inject.Inject

class GetFeedService
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
    ) : GetFeedUseCase {
        override suspend fun getFeed(): List<Tuit>? {
            val token = localData.getLoginToken()
            token == null && return emptyList()
            return api.getFeed(1, token.toString())
        }

        override suspend fun getUserTuitsByEmail(name: String): List<Tuit>? {
            val tuits = this.getFeed()?.filter { it.author == name }
            return tuits
        }
    }
