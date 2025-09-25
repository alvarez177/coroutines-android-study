package com.sazcode.coroutinas.shared

interface TimeCapsule<State> {

    fun addState(state: State)
    fun selectState(position: Int)
    fun getStates(): List<State>
    fun getPreviousState(): State?
}