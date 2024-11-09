package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import javax.inject.Inject

class GetFeed
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
    ) : GetFeedService {
    override suspend fun getFeed(page: Int): List<Tuit>? {
        val token = localData.getLoginToken()
        token == null && return emptyList()
        val tuitList = api.getFeed(page, token.toString())
        return tuitList
    }


    override suspend fun getUserTuitsByEmail(name: String): List<Tuit>? {



        val tuits = this.getFeed(2)?.filter { it.author == name }
        return tuits
    }

}