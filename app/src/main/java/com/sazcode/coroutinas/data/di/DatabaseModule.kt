package com.sazcode.coroutinas.data.di

import android.content.Context
import androidx.room.Room
import com.sazcode.coroutinas.data.localsource.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "kbra"
        )
            //.addMigrations(DatabaseMigrations.MIGRATION_1_TO_2)
            .build()
    }

    @Provides
    fun provideDragonBallCharacterDao(appDatabase: AppDatabase) =
        appDatabase.dragonBallCharacterDao()

    @Provides
    fun provideRickAndMortyCharacterDao(appDatabase: AppDatabase) =
        appDatabase.rickAndMortyCharacterDao()

}