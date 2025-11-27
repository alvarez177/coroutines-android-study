package com.sazcode.coroutinas.data.localsource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter

@Entity(tableName = "rickandmortycharacterentity")
data class RickAndMortyCharacterEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val status: String,
    @ColumnInfo
    val species: String,
    @ColumnInfo
    val gender: String,
    @ColumnInfo
    val image: String,
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
) {

    constructor(rickAndMortyCharacter: RickAndMortyCharacter) : this(
        id = rickAndMortyCharacter.id,
        name = rickAndMortyCharacter.name,
        status = rickAndMortyCharacter.status,
        species = rickAndMortyCharacter.species,
        gender = rickAndMortyCharacter.gender,
        image = rickAndMortyCharacter.image
    )

    fun toRickAndMortyCharacter(): RickAndMortyCharacter {
        return RickAndMortyCharacter(
            id = id,
            name = name,
            status = status,
            species = species,
            gender = gender,
            image = image
        )
    }
}