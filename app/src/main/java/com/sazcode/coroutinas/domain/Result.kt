package com.sazcode.coroutinas.domain

sealed class Result<out D> {
    data class Success<out D>(val data: D) : Result<D>()
    data class Error(val error: String) : Result<Nothing>()
}