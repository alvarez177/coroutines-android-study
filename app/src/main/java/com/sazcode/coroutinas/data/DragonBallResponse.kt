package com.sazcode.coroutinas.data

import kotlinx.serialization.SerialName

data class DragonBallResponse(
    @SerialName("items")
    val dragonBallCharactersResponse: List<DragonBallCharacterResponse>,
    @SerialName("meta")
    val metaResponse: DragonBallMetaResponse
)