package com.sazcode.coroutinas.shared

abstract class Transform<Type1, ToType2> {

    abstract fun transform(type1: Type1): ToType2

    open fun transformCollection(types1: List<Type1>): List<ToType2> {
        return types1.map { type1 ->
            transform(type1)
        }
    }
}