package com.sazcode.coroutinas.domain

import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter
import javax.inject.Inject

class GetRickAndMortyCharactersUseCase @Inject constructor(
    private val repository: AnimeRepository
) {

    suspend operator fun invoke(): Result<List<RickAndMortyCharacter>> {
        return repository.getRickAndMortyAllCharacters()
    }
}