package com.sazcode.coroutinas.domain

interface AnimeRepository {

    suspend fun getDragonBallCharacters(): Result<List<DragonBallCharacter>>
}