package com.sazcode.coroutinas.data.localsource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DragonBallCharacterDao {

    @Query("SELECT * FROM dragonballcharacterentity")
    fun getAll(): List<DragonBallCharacterEntity>

    @Insert
    fun saveAll(dragonBallCharactersEntity: List<DragonBallCharacterEntity>)

    @Delete
    fun delete(dragonBallCharacterEntity: DragonBallCharacterEntity)

    @Query("SELECT EXISTS(SELECT * FROM dragonballcharacterentity)")
    fun hasAnyDragonBallCharacter(): Boolean

}