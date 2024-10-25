package ar.edu.unlam.mobile.scaffolding.data.network.di

import ar.edu.unlam.mobile.scaffolding.data.network.api.NotTwitterApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiProviderModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://tuiter.fragua.com.ar/api/v1/")
            .addConverterFactory(
                retrofit2.converter.moshi.MoshiConverterFactory
                    .create(),
            ).client(OkHttpClient.Builder().build())
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): NotTwitterApiClient = retrofit.create(NotTwitterApiClient::class.java)
}
