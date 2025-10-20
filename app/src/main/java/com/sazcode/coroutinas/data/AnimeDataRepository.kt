package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.data.mapper.DragonBallCharacterResponseToDragonBallCharacterMapper
import com.sazcode.coroutinas.data.mapper.RickAndMortyCharacterResponseToRickAndMortyCharacter
import com.sazcode.coroutinas.domain.AnimeRepository
import com.sazcode.coroutinas.domain.model.DragonBallCharacter
import com.sazcode.coroutinas.domain.Result
import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeDataRepository @Inject constructor(
    private val animeService: AnimeService,
    private val toDragonBallCharacterMapper: DragonBallCharacterResponseToDragonBallCharacterMapper,
    private val rickAndMortyService: RickAndMortyService,
    private val toRickAndMortyCharacterMapper: RickAndMortyCharacterResponseToRickAndMortyCharacter
): AnimeRepository {

    override suspend fun getDragonBallCharacters(): Result<List<DragonBallCharacter>> {
        try {
            val dragonBallResponse = animeService.getDragonBallResponse()
            val dragonBallCharactersResponse = dragonBallResponse.dragonBallCharactersResponse
            if (dragonBallCharactersResponse != null) {
                val dragonBallCharacters = toDragonBallCharacterMapper.transformCollection(dragonBallCharactersResponse)
                return Result.Success(dragonBallCharacters)
            } else {
                return Result.Error("Null items response")
            }
        } catch (exception: Exception) {
            return Result.Error(exception.message.orEmpty())
        }
    }

    override suspend fun getRickAndMortyAllCharacters(): Result<List<RickAndMortyCharacter>> {
        try {
            val rickAndMortyResponse = rickAndMortyService.getRickAndMortyResponse()
            val rickAndMortyCharactersResponse = rickAndMortyResponse.rickAndMortyCharactersResponse
            if (rickAndMortyCharactersResponse != null) {
                val rickAndMortyCharacters = toRickAndMortyCharacterMapper.transformCollection(rickAndMortyCharactersResponse)
                return Result.Success(rickAndMortyCharacters)
            } else {
                return Result.Error("Null items response")

            }
        } catch (exception: Exception) {
            return Result.Error(exception.message.orEmpty())
        }
    }
}