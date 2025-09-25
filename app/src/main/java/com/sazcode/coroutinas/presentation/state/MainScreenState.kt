package com.sazcode.coroutinas.presentation.state

import com.sazcode.coroutinas.presentation.model.DragonBallCharacterUI

data class MainScreenState(
    val isDragonBallSectionLoading: Boolean = false,
    val dragonBallCharacters: List<DragonBallCharacterUI> = emptyList()
) : Reducer.ViewState