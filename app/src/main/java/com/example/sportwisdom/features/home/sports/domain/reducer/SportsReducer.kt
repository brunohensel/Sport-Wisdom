package com.example.sportwisdom.features.home.sports.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.home.sports.domain.state.SportState
import com.example.sportwisdom.features.home.sports.domain.state.SportSyncState
import javax.inject.Inject

class SportsReducer @Inject constructor() : Reducer<SportState, com.example.sportwisdom.common.utils.BaseAction<*>> {

  override fun invoke(currentState: SportState, action: com.example.sportwisdom.common.utils.BaseAction<*>): SportState {
    return when (action) {
      com.example.sportwisdom.common.utils.BaseAction.Executing           -> currentState.copy(syncState = SportSyncState.Loading)
      com.example.sportwisdom.common.utils.BaseAction.EmptyResult         -> currentState.copy(syncState = SportSyncState.Empty)
      is com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess<*> -> currentState.copy(sportsModel = action.value as SportsModel, syncState = SportSyncState.Content)
      is com.example.sportwisdom.common.utils.BaseAction.Failed           -> currentState.copy(syncState = SportSyncState.Message(action.reason))
      else                                                                -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}