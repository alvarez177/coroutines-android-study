package com.sazcode.coroutinas.data.di

import com.sazcode.coroutinas.data.AnimeService
import com.sazcode.coroutinas.data.MyRetrofitInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dragonball-api.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: MyRetrofitInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // <- aquí lo agregas
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeService(retrofit: Retrofit): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }

}