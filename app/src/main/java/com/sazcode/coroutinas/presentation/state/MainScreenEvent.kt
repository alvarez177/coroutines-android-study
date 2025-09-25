package com.sazcode.coroutinas.presentation.state

import com.sazcode.coroutinas.presentation.model.DragonBallCharacterUI

sealed class MainScreenEvent: Reducer.ViewEvent {
    data class UpdateDragonBallSectionLoading(val isLoading: Boolean) : MainScreenEvent()
    data class UpdateDragonBallCharacters(val dragonBallCharacters: List<DragonBallCharacterUI>): MainScreenEvent()
}