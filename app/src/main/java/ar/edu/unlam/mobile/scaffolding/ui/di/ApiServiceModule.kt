package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.PostNewTuitService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeed
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.PostNewTuit
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.GetUserData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiServiceModule {
    @Binds
    abstract fun provideGetUserService(getUserService: GetUserData): GetUserService

    @Binds
    abstract fun provideNewTuitService(newTuitService: PostNewTuit): PostNewTuitService

    @Binds
    abstract fun provideTuitFeedService(newFeedService: GetFeed): GetFeedService
}
