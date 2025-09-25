package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.domain.AnimeRepository
import com.sazcode.coroutinas.domain.DragonBallCharacter
import com.sazcode.coroutinas.domain.Result
import javax.inject.Inject

class AnimeDataRepository @Inject constructor(
    private val animeService: AnimeService,
    private val toDragonBallCharacterMapper: DragonBallCharacterResponseToDragonBallCharacterMapper
): AnimeRepository {

    override suspend fun getDragonBallCharacters(): Result<List<DragonBallCharacter>> {
        val dragonBallCharactersResponse = animeService.getDragonBallCharacters()
        val dragonBallCharacters = toDragonBallCharacterMapper.transformCollection(dragonBallCharactersResponse)
        return Result.Success(dragonBallCharacters)
    }
}