package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.data.network.UserRepositoryImplementation
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindUSerRepository(userRepositoryImpl: UserRepositoryImplementation): UserRepository
}
