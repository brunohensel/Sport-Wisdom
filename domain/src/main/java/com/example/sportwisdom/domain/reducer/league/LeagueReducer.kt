package com.example.sportwisdom.domain.reducer.league

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.LeagueDto
import com.example.sportwisdom.domain.reducer.league.state.LeagueState
import com.example.sportwisdom.domain.reducer.league.state.LeagueSyncState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LeagueReducer @Inject constructor() : Reducer<LeagueState, BaseAction<*>> {

  override fun invoke(currentState: LeagueState, action: BaseAction<*>): LeagueState {
    return when (action) {
      BaseAction.Executing           -> currentState.copy(syncState = LeagueSyncState.Loading)
      BaseAction.EmptyResult         -> currentState.copy(syncState = LeagueSyncState.Empty)
      is BaseAction.RemoteSuccess<*> -> currentState.copy(leagueModel = action.value as List<LeagueDto>, syncState = LeagueSyncState.Content)
      is BaseAction.Failed           -> currentState.copy(syncState = LeagueSyncState.Message(action.reason))
      else                           -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}