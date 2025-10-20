package com.sazcode.coroutinas.domain.usecase

import com.sazcode.coroutinas.domain.AnimeRepository
import com.sazcode.coroutinas.domain.Result
import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter
import javax.inject.Inject

class GetRickAndMortyCharactersUseCase @Inject constructor(
    private val repository: AnimeRepository
) {

    suspend operator fun invoke(): Result<List<RickAndMortyCharacter>> {
        return repository.getRickAndMortyAllCharacters()
    }
}