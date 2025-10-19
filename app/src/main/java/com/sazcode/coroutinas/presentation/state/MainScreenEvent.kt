package com.sazcode.coroutinas.presentation.state

import com.sazcode.coroutinas.presentation.model.MixedContentUI
import com.sazcode.coroutinas.presentation.model.SectionUI

sealed class MainScreenEvent: Reducer.ViewEvent {
    data class UpdateSectionLoading(val sectionId: String, val isLoading: Boolean) : MainScreenEvent()
    data class UpdateSectionSuccess(val sectionId: String, val items: List<MixedContentUI>) : MainScreenEvent()
    data class UpdateSectionError(val sectionId: String, val error: String) : MainScreenEvent()
    data class AddOrReplaceSection(val section: SectionUI) : MainScreenEvent()
}