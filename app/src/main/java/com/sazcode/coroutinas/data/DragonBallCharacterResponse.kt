package com.sazcode.coroutinas.data

data class DragonBallCharacterResponse(
    val id: String,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race : String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String
)