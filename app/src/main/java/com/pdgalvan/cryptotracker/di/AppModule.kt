package com.pdgalvan.cryptotracker.di

import com.pdgalvan.cryptotracker.data.datasource.remote.CoinService
import com.pdgalvan.cryptotracker.data.repository.CoinRepositoryImpl
import com.pdgalvan.cryptotracker.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @ApiEndpoint
    fun provideApiEndpoint() : String = "https://api.coincap.io/v2/"

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideJsonConverterFactory() : Converter.Factory = Json{ignoreUnknownKeys =  true}.asConverterFactory("application/json".toMediaType())

    @Provides
    fun providerRestAdapter(
        @ApiEndpoint apiEndpoint: String,
        httpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(apiEndpoint)
        .addConverterFactory(converterFactory)
        .client(httpClient)
        .build()

    @Provides
    fun provideCoinService(restAdapter: Retrofit) : CoinService = restAdapter.create()

    @Provides
    fun provideCoinRepository(coinService: CoinService): CoinRepository = CoinRepositoryImpl(coinService)

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint