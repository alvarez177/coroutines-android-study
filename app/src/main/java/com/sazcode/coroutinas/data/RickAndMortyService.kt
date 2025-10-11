package com.sazcode.coroutinas.data

import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getRickAndMortyResponse(): RickAndMortyResponse
}