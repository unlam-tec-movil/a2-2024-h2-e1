package ar.edu.unlam.mobile.scaffolding.data.local.localdata.di

import android.content.Context
import android.content.SharedPreferences
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.LastMessageManager
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.UserDataManager
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.services.LastMessageManagerImplementation
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.services.UserDataManagerImplementation
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
    @Singleton
    fun provideUserPreferencesManager(sharedPreferences: SharedPreferences): UserDataManager =
        UserDataManagerImplementation(sharedPreferences)

    @Provides
    @Singleton
    fun provideDraftPreferencesManager(sharedPreferences: SharedPreferences): LastMessageManager =
        LastMessageManagerImplementation(sharedPreferences)
}
