package com.sazcode.coroutinas.data

import retrofit2.http.GET

interface AnimeService {

    @GET("characters")
    suspend fun getDragonBallResponse(): DragonBallResponse
}