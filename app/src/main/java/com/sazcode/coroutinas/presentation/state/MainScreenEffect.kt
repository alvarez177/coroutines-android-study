package com.sazcode.coroutinas.presentation.state

sealed class MainScreenEffect: Reducer.ViewEffect {
    data class NavigateToCharacterDetail(val characterId: String): MainScreenEffect()
}