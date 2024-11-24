package ar.edu.unlam.mobile.scaffolding.data.network.api.di

import ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces.LoginApiClient
import ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces.TuitApiClient
import ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces.UserApiClient
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
    fun provideApiLoginImplementation(retrofit: Retrofit): LoginApiClient =
        retrofit.create(
            LoginApiClient::class.java,
        )

    @Singleton
    @Provides
    fun provideApiTuitImplementation(retrofit: Retrofit): TuitApiClient =
        retrofit.create(
            TuitApiClient::class.java,
        )

    @Singleton
    @Provides
    fun provideApiUserImplementation(retrofit: Retrofit): UserApiClient =
        retrofit.create(
            UserApiClient::class.java,
        )
}
