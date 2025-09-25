package com.sazcode.coroutinas.presentation

import com.sazcode.coroutinas.domain.DragonBallCharacter
import com.sazcode.coroutinas.domain.GetDragonBallCharactersUseCase
import com.sazcode.coroutinas.domain.Result
import com.sazcode.coroutinas.presentation.state.MainScreenEffect
import com.sazcode.coroutinas.presentation.state.MainScreenEvent
import com.sazcode.coroutinas.presentation.state.MainScreenReducer
import com.sazcode.coroutinas.presentation.state.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DragonBallCharactersViewModel @Inject constructor(
    private val getDragonBallCharactersUseCase: GetDragonBallCharactersUseCase,
    private val characterDomainToUIMapper: DragonBallCharacterToDragonBallCharacterUIMapper
) : BaseViewModel<MainScreenState, MainScreenEvent, MainScreenEffect>(
    initialState = MainScreenState(),
    reducer = MainScreenReducer()
) {

    override suspend fun initialDataLoad() {
        sendEvent(MainScreenEvent.UpdateDragonBallSectionLoading(isLoading = true))
        val result: Result<List<DragonBallCharacter>> = getDragonBallCharactersUseCase.invoke()
        when (result) {
            is Result.Success -> {
                sendEvent(MainScreenEvent.UpdateDragonBallSectionLoading(isLoading = false))
                val dragonBallCharactersUI = characterDomainToUIMapper.transformCollection(result.data)
                sendEvent(MainScreenEvent.UpdateDragonBallCharacters(dragonBallCharactersUI))
            }
            is Result.Error -> {
                sendEvent(MainScreenEvent.UpdateDragonBallSectionLoading(isLoading = false))
                // sendEffect(MainScreenEffect.ShowError(dragonBallCharacters.error))
            }
        }
    }

}