package com.sazcode.coroutinas.data.di

import com.sazcode.coroutinas.data.AnimeService
import com.sazcode.coroutinas.data.MyRetrofitInterceptor
import com.sazcode.coroutinas.data.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val DRAGON_BALL_BASE_URL = "https://dragonball-api.com/api/"
    private const val RICK_AND_MORTY_BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    @Named(RetrofitNamedInstance.DRAGON_BALL_NAMED)
    fun provideDragonBallRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(DRAGON_BALL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @Named(RetrofitNamedInstance.RICK_AND_MORTY_NAMED)
    fun provideRickAndMortyRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(RICK_AND_MORTY_BASE_URL)
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
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeService(
        @Named(RetrofitNamedInstance.DRAGON_BALL_NAMED)
        retrofit: Retrofit
    ): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRickAndMortyService(
        @Named(RetrofitNamedInstance.RICK_AND_MORTY_NAMED)
        retrofit: Retrofit
    ): RickAndMortyService {
        return retrofit.create(RickAndMortyService::class.java)
    }

}