package com.sazcode.coroutinas.data.mapper

import com.sazcode.coroutinas.data.RickAndMortyCharacterResponse
import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter
import com.sazcode.coroutinas.shared.Transform
import jakarta.inject.Inject

class RickAndMortyCharacterResponseToRickAndMortyCharacter @Inject constructor(): Transform<RickAndMortyCharacterResponse, RickAndMortyCharacter>() {

    override fun transform(type1: RickAndMortyCharacterResponse): RickAndMortyCharacter {
        return RickAndMortyCharacter(
            id = type1.id,
            name = type1.name,
            status = type1.status,
            species = type1.species,
            gender = type1.gender,
            image = type1.image
        )
    }
}