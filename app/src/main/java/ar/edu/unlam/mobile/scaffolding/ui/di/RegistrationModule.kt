package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.login.services.UserRegistrationService
import ar.edu.unlam.mobile.scaffolding.domain.login.usecases.UserRegistrationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RegistrationModule {
    @Binds
    abstract fun bindRegistrationRepository(registrationRepositoryImpl: UserRegistrationService): UserRegistrationUseCase
}
