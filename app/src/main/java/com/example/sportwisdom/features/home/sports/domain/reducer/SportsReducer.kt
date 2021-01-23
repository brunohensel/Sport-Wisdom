package com.example.sportwisdom.features.home.sports.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.home.sports.domain.state.SportState
import com.example.sportwisdom.features.home.sports.domain.state.SportSyncState
import javax.inject.Inject

class SportsReducer @Inject constructor() : Reducer<SportState, BaseAction> {

  override fun invoke(currentState: SportState, action: BaseAction): SportState {
    return when (action){
      BaseAction.Executing     -> currentState.copy(syncState = SportSyncState.Loading)
      BaseAction.EmptyResult   -> currentState.copy(syncState = SportSyncState.Empty)
      is BaseAction.Success<*> -> currentState.copy(sportsModel = action.value as SportsModel, syncState = SportSyncState.Content)
      is BaseAction.Failed     -> currentState.copy(syncState = SportSyncState.Message(action.reason))
    }
  }
}