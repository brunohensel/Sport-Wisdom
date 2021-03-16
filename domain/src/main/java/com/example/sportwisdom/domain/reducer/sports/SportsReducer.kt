package com.example.sportwisdom.domain.reducer.sports

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.SportsModel
import com.example.sportwisdom.domain.reducer.sports.state.SportState
import com.example.sportwisdom.domain.reducer.sports.state.SportSyncState
import javax.inject.Inject

class SportsReducer @Inject constructor() : Reducer<SportState, BaseAction<*>> {

  override fun invoke(currentState: SportState, action: BaseAction<*>): SportState {
    return when (action) {
      BaseAction.Executing           -> currentState.copy(syncState = SportSyncState.Loading)
      BaseAction.EmptyResult         -> currentState.copy(syncState = SportSyncState.Empty)
      is BaseAction.RemoteSuccess<*> -> currentState.copy(sportsModel = action.value as SportsModel, syncState = SportSyncState.Content)
      is BaseAction.Failed           -> currentState.copy(syncState = SportSyncState.Message(action.reason))
      else                           -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}