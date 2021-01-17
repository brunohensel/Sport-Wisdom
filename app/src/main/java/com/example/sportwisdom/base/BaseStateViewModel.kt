package com.example.sportwisdom.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber

@FlowPreview
abstract class BaseStateViewModel<State, Event, Action>(
  private val initialState: State,
  private val reducer: (State, Action) -> State,
  private val action: (Event) -> Flow<Action>
) : ViewModel() {

  //Producer
  private val event = MutableSharedFlow<Event>()

  //Consumer
  val state: StateFlow<State> = toState()

  suspend fun process(event: Flow<Event>) {
    event.collect {
      this.event.emit(it)//suspend
    }
  }

  private fun toState(): StateFlow<State> {
    return event
      .onEach { Timber.d("Event $it") }
      .flatMapConcat { event -> action(event) }
      .onEach { Timber.i("Action $it") }
      .map { action -> reducer(state.value, action) }
      .onEach { Timber.w("State $it") }
      .onCompletion { Timber.d("onCompletion for $state") }
      .stateIn(viewModelScope, SharingStarted.Eagerly, initialState)
  }
}