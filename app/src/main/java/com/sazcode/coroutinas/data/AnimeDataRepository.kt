package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.domain.AnimeRepository
import com.sazcode.coroutinas.domain.DragonBallCharacter
import com.sazcode.coroutinas.domain.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeDataRepository @Inject constructor(
    private val animeService: AnimeService,
    private val toDragonBallCharacterMapper: DragonBallCharacterResponseToDragonBallCharacterMapper
): AnimeRepository {

    override suspend fun getDragonBallCharacters(): Result<List<DragonBallCharacter>> {
        val dragonBallResponse = animeService.getDragonBallResponse()
        val dragonBallCharactersResponse = dragonBallResponse.dragonBallCharactersResponse
        if (dragonBallCharactersResponse != null) {
            val dragonBallCharacters = toDragonBallCharacterMapper.transformCollection(dragonBallCharactersResponse)
            return Result.Success(dragonBallCharacters)
        } else {
            return Result.Error("Null items response")
        }
    }
}