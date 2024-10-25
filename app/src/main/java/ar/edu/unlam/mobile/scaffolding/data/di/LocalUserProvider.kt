package ar.edu.unlam.mobile.scaffolding.data.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalUserProvider {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): UsersDatabase =

        Room
            .databaseBuilder(
                context,
                UsersDatabase::class.java,
                "real_androids_database",
            ).build()
}
