package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.data.response.dragonballz.DragonBallResponse
import retrofit2.http.GET

interface AnimeService {

    @GET("characters")
    suspend fun getDragonBallResponse(): DragonBallResponse
}