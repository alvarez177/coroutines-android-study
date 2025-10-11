package com.sazcode.coroutinas.domain

import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter

interface AnimeRepository {

    suspend fun getDragonBallCharacters(): Result<List<DragonBallCharacter>>

    suspend fun getRickAndMortyAllCharacters(): Result<List<RickAndMortyCharacter>>
}