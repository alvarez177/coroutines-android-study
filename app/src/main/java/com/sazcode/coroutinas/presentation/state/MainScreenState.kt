package com.sazcode.coroutinas.presentation.state

import com.sazcode.coroutinas.presentation.model.DragonBallCharacterUI
import com.sazcode.coroutinas.presentation.model.SectionUI

data class MainScreenState(
    val isDragonBallSectionLoading: Boolean = true,
    val dragonBallCharacters: List<DragonBallCharacterUI> = emptyList(),
    val sections: List<SectionUI> = emptyList(),
    val error: String = ""
) : Reducer.ViewState