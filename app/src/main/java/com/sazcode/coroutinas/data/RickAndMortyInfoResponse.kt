package com.sazcode.coroutinas.data

data class RickAndMortyInfoResponse (
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)