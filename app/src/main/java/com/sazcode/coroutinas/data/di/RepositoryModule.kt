package com.sazcode.coroutinas.data.di

import com.sazcode.coroutinas.data.AnimeDataRepository
import com.sazcode.coroutinas.domain.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAnimeRepository(
        impl: AnimeDataRepository
    ): AnimeRepository

}