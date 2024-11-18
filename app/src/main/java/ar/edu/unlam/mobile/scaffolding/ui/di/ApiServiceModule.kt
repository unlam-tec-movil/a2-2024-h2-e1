package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.favorites.service.FavoritesService
import ar.edu.unlam.mobile.scaffolding.domain.favorites.usecase.FavoritesUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.DraftService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.LikeService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.PostNewTuitService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.DraftUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeedUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.LikeUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.PostNewTuitUseCase
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.GetUserDataUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiServiceModule {
    @Binds
    abstract fun provideGetUserService(getUserService: GetUserService): GetUserDataUseCase

    @Binds
    abstract fun provideDraftService(draftService: DraftService): DraftUseCase

    @Binds
    abstract fun provideNewTuitService(newTuitService: PostNewTuitService): PostNewTuitUseCase

    @Binds
    abstract fun provideTuitFeedService(newFeedService: GetFeedService): GetFeedUseCase

    @Binds
    abstract fun provideLikeService(likeServiceImplementation: LikeService): LikeUseCase

    @Binds
    abstract fun provideFavServices(draftService: FavoritesService): FavoritesUseCase
}
