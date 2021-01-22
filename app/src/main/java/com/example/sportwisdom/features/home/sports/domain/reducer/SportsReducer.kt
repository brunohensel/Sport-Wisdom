package com.example.sportwisdom.features.home.sports.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.home.sports.domain.state.HomeState
import com.example.sportwisdom.features.home.sports.domain.state.HomeSyncState
import javax.inject.Inject

class SportsReducer @Inject constructor() : Reducer<HomeState, BaseAction> {

  override fun invoke(currentState: HomeState, action: BaseAction): HomeState {
    return when (action){
      BaseAction.Executing     -> currentState.copy(syncState = HomeSyncState.Loading)
      is BaseAction.Success<*> -> currentState.copy(sportsModel = action.value as SportsModel, syncState = HomeSyncState.Content)
      is BaseAction.Failed     -> currentState.copy(syncState = HomeSyncState.Message(action.reason))
    }
  }
}