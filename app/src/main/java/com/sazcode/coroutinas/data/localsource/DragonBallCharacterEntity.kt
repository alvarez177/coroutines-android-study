package com.sazcode.coroutinas.data.localsource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sazcode.coroutinas.domain.model.DragonBallCharacter

@Entity(tableName = "dragonballcharacterentity")
data class DragonBallCharacterEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val ki: String,
    @ColumnInfo(name = "max_ki")
    val maxKi: String,
    @ColumnInfo
    val race: String,
    @ColumnInfo
    val gender: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    val affiliation: String,
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
) {
    constructor(dragonBallCharacter: DragonBallCharacter) : this(
        id = dragonBallCharacter.id,
        name = dragonBallCharacter.name,
        ki = dragonBallCharacter.ki,
        maxKi = dragonBallCharacter.maxKi,
        race = dragonBallCharacter.race,
        gender = dragonBallCharacter.gender,
        description = dragonBallCharacter.description,
        image = dragonBallCharacter.image,
        affiliation = dragonBallCharacter.affiliation
    )

    fun toDragonBallCharacterDomain(): DragonBallCharacter {
        return DragonBallCharacter(
            id = id,
            name = name,
            ki = ki,
            maxKi = maxKi,
            race = race,
            gender = gender,
            description = description,
            image = image,
            affiliation = affiliation
        )
    }
}
