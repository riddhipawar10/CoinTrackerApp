package com.example.cointrackerapp.di

import com.example.cointrackerapp.data.repository.CoinRepositoryImpl
import com.example.cointrackerapp.domain.repository.CoinRepository
import com.example.cointrackerapp.domain.usecase.CoinUseCase
import com.example.cointrackerapp.remote.api.CoinAPI
import com.example.cointrackerapp.remote.utils.CoinRemoteUtil.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinApi(retrofit: Retrofit): CoinAPI {
       return retrofit.create(CoinAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCoinRepository(api: CoinAPI): CoinRepository {
        return CoinRepositoryImpl(api)
    }


    @Singleton
    @Provides
    fun provideCoinUseCase(repository: CoinRepository): CoinUseCase {
        return CoinUseCase(repository)
    }
}