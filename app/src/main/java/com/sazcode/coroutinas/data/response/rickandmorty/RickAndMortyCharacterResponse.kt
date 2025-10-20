package com.sazcode.coroutinas.data.response.rickandmorty

data class RickAndMortyCharacterResponse(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)