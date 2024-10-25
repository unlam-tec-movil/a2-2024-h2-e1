package ar.edu.unlam.mobile.scaffolding.data.local.di

import android.content.Context
import android.content.SharedPreferences
import ar.edu.unlam.mobile.scaffolding.data.local.LocalDataManager
import ar.edu.unlam.mobile.scaffolding.data.local.LocalDataManagerImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSharedPreferencesProvider {
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext applicationContext: Context,
    ): SharedPreferences = applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    @Provides
    fun providePreferencesManager(sharedPreferences: SharedPreferences): LocalDataManager =
        LocalDataManagerImplementation(sharedPreferences)
}
