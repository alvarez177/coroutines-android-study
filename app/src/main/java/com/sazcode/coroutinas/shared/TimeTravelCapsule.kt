package com.sazcode.coroutinas.shared

import androidx.compose.runtime.State

class TimeTravelCapsule<State>(
    private val onStateSelected: (State) -> Unit
) : TimeCapsule<State> {

    private val states = mutableListOf<State>()

    override fun addState(state: State) {
        states.add(state)
    }

    override fun selectState(position: Int) {
        onStateSelected(states[position])
    }

    override fun getStates(): List<State> {
        return states
    }

    override fun getPreviousState(): State? {
        val lastIndex = getStates().size - 1
        return if (lastIndex > 0) {
            states[lastIndex - 1]
        } else {
            null
        }
    }
}