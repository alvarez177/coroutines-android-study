package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.data.localsource.DragonBallCharacterDao
import com.sazcode.coroutinas.data.localsource.DragonBallCharacterEntity
import com.sazcode.coroutinas.data.localsource.RickAndMortyCharacterDao
import com.sazcode.coroutinas.data.localsource.RickAndMortyCharacterEntity
import com.sazcode.coroutinas.data.mapper.DragonBallCharacterResponseToDragonBallCharacterMapper
import com.sazcode.coroutinas.data.mapper.RickAndMortyCharacterResponseToRickAndMortyCharacter
import com.sazcode.coroutinas.domain.AnimeRepository
import com.sazcode.coroutinas.domain.Result
import com.sazcode.coroutinas.domain.model.DragonBallCharacter
import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeDataRepository @Inject constructor(
    private val animeService: AnimeService,
    private val toDragonBallCharacterMapper: DragonBallCharacterResponseToDragonBallCharacterMapper,
    private val rickAndMortyService: RickAndMortyService,
    private val toRickAndMortyCharacterMapper: RickAndMortyCharacterResponseToRickAndMortyCharacter,
    private val dragonBallCharacterDao: DragonBallCharacterDao,
    private val rickAndMortyCharacterDao: RickAndMortyCharacterDao
) : AnimeRepository {

    override suspend fun getDragonBallCharacters(): Result<List<DragonBallCharacter>> {
        return try {
            // 1. Si hay datos locales → retornarlos
            if (dragonBallCharacterDao.hasAnyDragonBallCharacter()) {
                val localCharacters = dragonBallCharacterDao.getAll()
                    .map { it.toDragonBallCharacterDomain() }

                return Result.Success(localCharacters)
            }

            // 2. No hay locales → pedir al remoto
            val dragonBallResponse = animeService.getDragonBallResponse()
            val remoteItems = dragonBallResponse.dragonBallCharactersResponse
                ?: return Result.Error("Null items response")

            // 3. Mapear y guardar
            val domainCharacters = toDragonBallCharacterMapper
                .transformCollection(remoteItems)

            val entities = domainCharacters.map { DragonBallCharacterEntity(it) }
            dragonBallCharacterDao.saveAll(entities)

            Result.Success(domainCharacters)

        } catch (e: Exception) {
            Result.Error(e.message.orEmpty())
        }
    }

    override suspend fun getRickAndMortyAllCharacters(): Result<List<RickAndMortyCharacter>> {
        return try {
            if (rickAndMortyCharacterDao.hasAnyRickAndMortyCharacter()) {
                val localRickAndMortyCharacters = rickAndMortyCharacterDao.getAll().map {
                    it.toRickAndMortyCharacter()
                }
                return Result.Success(localRickAndMortyCharacters)
            }

            val rickAndMortyResponse = rickAndMortyService.getRickAndMortyResponse()
            val rickAndMortyCharactersResponse = rickAndMortyResponse.rickAndMortyCharactersResponse
                ?: return Result.Error("Null items response")
            val rickAndMortyCharacters =
                toRickAndMortyCharacterMapper.transformCollection(rickAndMortyCharactersResponse)
            val rickAndMortyCharactersEntity =
                rickAndMortyCharacters.map { RickAndMortyCharacterEntity(it) }
            rickAndMortyCharacterDao.saveAll(rickAndMortyCharactersEntity)
             Result.Success(rickAndMortyCharacters)
        } catch (exception: Exception) {
            return Result.Error(exception.message.orEmpty())
        }
    }
}