package ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
import javax.inject.Inject

class GetFeed
    @Inject
    constructor(
        private val api: ApiRepository,
        private val localData: LocalDataRepository,
    ) : GetFeedService {
        override suspend fun getFeed(): List<Tuit>? {
            val token = localData.getLoginToken()
            token == null && return emptyList()
            var page = localData.getNavPage()
            var isLastPage = false
            while (!isLastPage) {
                val tuitlist = api.getFeed(page, token.toString())
                if (tuitlist.isEmpty()) {
                    Log.e("tuitlist", tuitlist.toString())
                    localData.steptToPreviousPage()
                    page = localData.getNavPage()
                    isLastPage = true
                } else {
                    Log.e("tuitlist", tuitlist.toString())
                    localData.steptToNextPage()
                    page = localData.getNavPage()
                }
            }
            Log.e("page", localData.getNavPage().toString())
            return api.getFeed(page, token.toString()).reversed()
        }

        override suspend fun getUserTuitsByEmail(name: String): List<Tuit>? {
            val tuits = this.getFeed()?.filter { it.author == name }
            return tuits
        }
    }
