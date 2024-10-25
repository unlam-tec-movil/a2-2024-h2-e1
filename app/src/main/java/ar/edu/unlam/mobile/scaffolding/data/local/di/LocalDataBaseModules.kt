package ar.edu.unlam.mobile.scaffolding.data.local.di

import ar.edu.unlam.mobile.scaffolding.data.local.repository.DataBaseRepository
import ar.edu.unlam.mobile.scaffolding.data.local.repository.DatabaseImplementation
import ar.edu.unlam.mobile.scaffolding.data.local.repository.RoomDataBaseRepository
import ar.edu.unlam.mobile.scaffolding.domain.repository.LocalUserDataBaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataBaseModules {
    @Binds
    abstract fun provideDatabaseImplem(localDataUserImplementation: DatabaseImplementation): LocalUserDataBaseRepository

    @Binds
    abstract fun provideRoomDataBaseRepository(databaseImplementation: RoomDataBaseRepository): DataBaseRepository
}
