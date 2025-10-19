package com.sazcode.coroutinas.presentation.state

class MainScreenReducer : Reducer<MainScreenState, MainScreenEvent, MainScreenEffect>{
    override fun reduce(
        previousState: MainScreenState,
        event: MainScreenEvent
    ): Pair<MainScreenState, MainScreenEffect?> {
        return when (event) {
            is MainScreenEvent.AddOrReplaceSection -> {
                val updatedSections = previousState.sections
                    .filterNot { it.id == event.section.id }
                    .plus(event.section)
                previousState.copy(sections = updatedSections) to null
            }

            is MainScreenEvent.UpdateSectionLoading -> {
                previousState.copy(
                    sections = previousState.sections.map {
                        if (it.id == event.sectionId) it.copy(isLoading = event.isLoading) else it
                    }
                ) to null
            }

            is MainScreenEvent.UpdateSectionSuccess -> {
                previousState.copy(
                    sections = previousState.sections.map {
                        if (it.id == event.sectionId) it.copy(isLoading = false, items = event.items, error = null)
                        else it
                    }
                ) to null
            }

            is MainScreenEvent.UpdateSectionError -> {
                previousState.copy(
                    sections = previousState.sections.map {
                        if (it.id == event.sectionId) it.copy(isLoading = false, error = event.error)
                        else it
                    }
                ) to null
            }
        }
    }
}