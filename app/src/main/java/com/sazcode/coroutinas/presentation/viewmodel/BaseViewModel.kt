package com.sazcode.coroutinas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sazcode.coroutinas.presentation.state.Reducer
import com.sazcode.coroutinas.shared.TimeCapsule
import com.sazcode.coroutinas.shared.TimeTravelCapsule
import com.squareup.picasso.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel<State : Reducer.ViewState, Event : Reducer.ViewEvent, Effect : Reducer.ViewEffect>(
    val initialState: State,
    private val reducer: Reducer<State, Event, Effect>
) : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)

    val state: StateFlow<State> by lazy {
        _state.onStart {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    initialDataLoad()
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000L),
            initialValue = initialState
        )
    }

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event>
        get() = _event.asSharedFlow()

    private val _effects = Channel<Effect>(capacity = Channel.Factory.CONFLATED)
    val effect = _effects.receiveAsFlow()

    val timeCapsule: TimeCapsule<State> = TimeTravelCapsule { storedState ->
        _state.tryEmit(storedState)
    }

    init {
        timeCapsule.addState(initialState)
    }

    open suspend fun initialDataLoad() {}

    fun sendEffect(effect: Effect) {
        _effects.trySend(effect)
    }

    fun sendEvent(event: Event) {
        val (newState, _) = reducer.reduce(_state.value, event)

        val success = _state.tryEmit(newState)

        if (BuildConfig.DEBUG && success) {
            timeCapsule.addState(newState)
        }
    }

    fun sendEventForEffect(event: Event) {
        val (newState, effect) = reducer.reduce(_state.value, event)

        val success = _state.tryEmit(newState)

        if (BuildConfig.DEBUG && success) {
            timeCapsule.addState(newState)
        }

        effect?.let {
            sendEffect(it)
        }
    }
}