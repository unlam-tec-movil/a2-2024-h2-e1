package ar.edu.unlam.mobile.scaffolding.domain.di

import ar.edu.unlam.mobile.scaffolding.data.repository.favorites.FavoritesApiService
import ar.edu.unlam.mobile.scaffolding.data.repository.login.LoginApiService
import ar.edu.unlam.mobile.scaffolding.data.repository.tuit.TuitApiService
import ar.edu.unlam.mobile.scaffolding.data.repository.user.UserApiService
import ar.edu.unlam.mobile.scaffolding.domain.favorites.repository.FavoritesRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.login.repository.LoginApiInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepositoryInterface
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataProviderModule {
    @Binds
    abstract fun provideDataLoginService(loginApiService: LoginApiService): LoginApiInterface

    @Binds
    abstract fun provideDataTuitService(tuitApiService: TuitApiService): TuitRepositoryInterface

    @Binds
    abstract fun provideDataUserService(userApiService: UserApiService): UserRepositoryInterface

    @Binds
    abstract fun provideDataFavoritesService(favoritesApiService: FavoritesApiService): FavoritesRepositoryInterface
}
