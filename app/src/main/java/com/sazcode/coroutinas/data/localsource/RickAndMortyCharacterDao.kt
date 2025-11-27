package com.sazcode.coroutinas.data.localsource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RickAndMortyCharacterDao {

    @Query("SELECT * FROM rickandmortycharacterentity")
    fun getAll(): List<RickAndMortyCharacterEntity>

    @Insert
    fun saveAll(rickAndMortyCharactersEntity: List<RickAndMortyCharacterEntity>)

    @Delete
    fun delete(rickAndMortyEntity: RickAndMortyCharacterEntity)

    @Query("SELECT EXISTS(SELECT * FROM rickandmortycharacterentity)")
    fun hasAnyRickAndMortyCharacter(): Boolean

}