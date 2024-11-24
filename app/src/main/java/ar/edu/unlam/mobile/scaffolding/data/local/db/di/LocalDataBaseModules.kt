package ar.edu.unlam.mobile.scaffolding.data.local.db.di

import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.FavoritesDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.SavedMessagesDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.data.local.db.interfaces.UserDatabaseInterface
import ar.edu.unlam.mobile.scaffolding.data.local.db.services.FavoritesDatabaseImplementation
import ar.edu.unlam.mobile.scaffolding.data.local.db.services.SavedMessagesDatabaseImplementation
import ar.edu.unlam.mobile.scaffolding.data.local.db.services.UserDatabaseImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataBaseModules {
    @Binds
    abstract fun provideFavoritesDataBaseRepository(
        favoritesDatabaseImplementation: FavoritesDatabaseImplementation,
    ): FavoritesDatabaseInterface

    @Binds
    abstract fun provideSavedMessagesDataBaseRepossitory(
        savedMessagesDatabaseImplementation: SavedMessagesDatabaseImplementation,
    ): SavedMessagesDatabaseInterface

    @Binds
    abstract fun provideUsersDataBaseRepository(userDatabaseImplementation: UserDatabaseImplementation): UserDatabaseInterface
}
