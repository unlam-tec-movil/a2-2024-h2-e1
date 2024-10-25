package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.GetUserData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetUserServiceModule {
    @Binds
    abstract fun provideGetUserService(getUserService: GetUserData): GetUserService
}
