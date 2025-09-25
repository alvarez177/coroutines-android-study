package com.sazcode.coroutinas.presentation.state

class MainScreenReducer : Reducer<MainScreenState, MainScreenEvent, MainScreenEffect>{

    override fun reduce(
        previousState: MainScreenState,
        event: MainScreenEvent
    ): Pair<MainScreenState, MainScreenEffect?> {
        return when (event) {
            is MainScreenEvent.UpdateDragonBallCharacters -> {
                previousState.copy(
                    dragonBallCharacters = event.dragonBallCharacters
                ) to null

            }
            is MainScreenEvent.UpdateDragonBallSectionLoading -> {
                previousState.copy(
                    isDragonBallSectionLoading = event.isLoading
                ) to null
            }
        }
    }
}