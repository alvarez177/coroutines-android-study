package com.sazcode.coroutinas.presentation.state

import com.sazcode.coroutinas.presentation.model.SectionUI

data class MainScreenState(
    val isDragonBallSectionLoading: Boolean = true,
    val sections: List<SectionUI> = emptyList(),
    val error: String = ""
) : Reducer.ViewState