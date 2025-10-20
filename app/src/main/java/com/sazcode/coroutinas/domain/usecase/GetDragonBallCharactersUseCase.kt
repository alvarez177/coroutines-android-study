package com.sazcode.coroutinas.domain.usecase

import com.sazcode.coroutinas.domain.AnimeRepository
import com.sazcode.coroutinas.domain.Result
import com.sazcode.coroutinas.domain.model.DragonBallCharacter
import javax.inject.Inject

class GetDragonBallCharactersUseCase @Inject constructor(private val repository: AnimeRepository) {

    suspend operator fun invoke(): Result<List<DragonBallCharacter>> {
        // throw Exception("Fake exception")
        return repository.getDragonBallCharacters()
    }
}