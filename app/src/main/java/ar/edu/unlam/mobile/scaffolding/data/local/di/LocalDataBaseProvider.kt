package ar.edu.unlam.mobile.scaffolding.data.local.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.LocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataBaseProvider {
    @Suppress("ktlint:standard:property-naming")
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): LocalDataBase =
        Room
            .databaseBuilder(
                context.applicationContext,
                LocalDataBase::class.java,
                "not_twitter_database",
            ).build()
}
