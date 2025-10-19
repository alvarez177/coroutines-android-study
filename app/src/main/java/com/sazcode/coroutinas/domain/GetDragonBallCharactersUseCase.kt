package com.sazcode.coroutinas.domain

import javax.inject.Inject

class GetDragonBallCharactersUseCase @Inject constructor(private val repository: AnimeRepository) {

    suspend operator fun invoke(): Result<List<DragonBallCharacter>> {
        // throw Exception("Fake exception")
        return repository.getDragonBallCharacters()
    }
}