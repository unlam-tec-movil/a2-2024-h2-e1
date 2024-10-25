package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.user.services.UserLocalDataService
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.AccessToUserLocalDataUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDataModule {
    @Binds
    abstract fun bindUserLocalData(userLocalData: UserLocalDataService): AccessToUserLocalDataUseCase
}
