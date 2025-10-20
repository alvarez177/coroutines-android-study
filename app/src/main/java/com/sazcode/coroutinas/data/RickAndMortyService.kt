package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.data.response.rickandmorty.RickAndMortyResponse
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getRickAndMortyResponse(): RickAndMortyResponse
}