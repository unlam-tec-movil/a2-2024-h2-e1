package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.login.services.UserLoginService
import ar.edu.unlam.mobile.scaffolding.domain.login.usecases.UserLoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {
    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: UserLoginService): UserLoginUseCase
}
