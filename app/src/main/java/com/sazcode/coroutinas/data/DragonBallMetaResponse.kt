package com.sazcode.coroutinas.data

data class DragonBallMetaResponse(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int,
)