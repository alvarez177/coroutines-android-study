package com.sazcode.coroutinas.data.response.dragonballz

import com.google.gson.annotations.SerializedName

data class DragonBallResponse(
    @SerializedName("items")
    val dragonBallCharactersResponse: List<DragonBallCharacterResponse>?,
    @SerializedName("meta")
    val metaResponse: DragonBallMetaResponse?
)