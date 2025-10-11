package com.sazcode.coroutinas.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class DragonBallResponse(
    @SerializedName("items")
    val dragonBallCharactersResponse: List<DragonBallCharacterResponse>?,
    @SerializedName("meta")
    val metaResponse: DragonBallMetaResponse?
)