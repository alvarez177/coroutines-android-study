package com.sazcode.coroutinas.data.localsource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DragonBallCharacterEntity::class, RickAndMortyCharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dragonBallCharacterDao(): DragonBallCharacterDao
    abstract fun rickAndMortyCharacterDao(): RickAndMortyCharacterDao

}