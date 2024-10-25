package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.data.local.UsersRoomRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.LocalUserDefaultRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.LocalUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.LocalUserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalUserServices {
    @Binds
    abstract fun bindLocalUserDataRepository(androidRepositoryImpl: LocalUserDefaultRepository): LocalUserDataRepository

    @Binds
    abstract fun bindLocalUserRepository(localUserRepositoryImpl: UsersRoomRepository): LocalUserRepository
}
