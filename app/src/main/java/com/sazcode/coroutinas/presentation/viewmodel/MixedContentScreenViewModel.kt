package com.sazcode.coroutinas.presentation.viewmodel

import com.sazcode.coroutinas.domain.usecase.GetDragonBallCharactersUseCase
import com.sazcode.coroutinas.domain.usecase.GetRickAndMortyCharactersUseCase
import com.sazcode.coroutinas.domain.Result
import com.sazcode.coroutinas.presentation.mapper.DragonBallCharacterToDragonBallCharacterUIMapper
import com.sazcode.coroutinas.presentation.mapper.RickAndMortyCharacterToRickAndMortyCharacterUIMapper
import com.sazcode.coroutinas.presentation.model.MixedContentUI
import com.sazcode.coroutinas.presentation.model.SectionIdentifier
import com.sazcode.coroutinas.presentation.model.SectionUI
import com.sazcode.coroutinas.presentation.state.MainScreenEffect
import com.sazcode.coroutinas.presentation.state.MainScreenEvent
import com.sazcode.coroutinas.presentation.state.MainScreenReducer
import com.sazcode.coroutinas.presentation.state.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MixedContentScreenViewModel @Inject constructor(
    private val getDragonBallCharactersUseCase: GetDragonBallCharactersUseCase,
    private val characterDomainToUIMapper: DragonBallCharacterToDragonBallCharacterUIMapper,
    private val getRickAndMortyCharactersUseCase: GetRickAndMortyCharactersUseCase,
    private val rickAndMortyCharacterUIMapper: RickAndMortyCharacterToRickAndMortyCharacterUIMapper
) : BaseViewModel<MainScreenState, MainScreenEvent, MainScreenEffect>(
    initialState = MainScreenState(),
    reducer = MainScreenReducer()
) {
    override suspend fun initialDataLoad() {
        coroutineScope {
            val dragonBallJob = async {
                runCatching {
                    loadSection(
                        id = SectionIdentifier.DRAGON_BALL_SECTION.id,
                        title = "Dragon Ball Z",
                        useCase = { getDragonBallCharactersUseCase.invoke() },
                        mapper = { characterDomainToUIMapper.transformCollection(it) }
                    )
                }.onFailure {
                    sendEvent(MainScreenEvent.UpdateSectionError("dragon_ball", "Error al cargar Dragon Ball..."))
                }
            }

            val rickAndMortyJob = async {
                runCatching {
                    loadSection(
                        id = SectionIdentifier.RICK_AND_MORTY_SECTION.id,
                        title = "Rick and Morty",
                        useCase = { getRickAndMortyCharactersUseCase.invoke() },
                        mapper = { rickAndMortyCharacterUIMapper.transformCollection(it) }
                    )
                }.onFailure {
                    sendEvent(MainScreenEvent.UpdateSectionError("rick_and_morty", "Error al cargar Rick and Morty..."))
                }
            }
            awaitAll(dragonBallJob, rickAndMortyJob)
        }
    }

    private suspend fun <T> loadSection(
        id: String,
        title: String,
        useCase: suspend () -> Result<List<T>>,
        mapper: (List<T>) -> List<MixedContentUI>
    ) {
        sendEvent(
            MainScreenEvent.AddOrReplaceSection(
                SectionUI(
                    id = id,
                    title = title,
                    isLoading = true
                )
            )
        )

        when (val result = useCase()) {
            is Result.Success -> {
                sendEvent(MainScreenEvent.UpdateSectionSuccess(id, mapper(result.data)))
            }

            is Result.Error -> {
                sendEvent(MainScreenEvent.UpdateSectionError(id, "Error al cargar $title"))
            }
        }
    }

}