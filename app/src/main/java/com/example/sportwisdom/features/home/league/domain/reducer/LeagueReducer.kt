package com.example.sportwisdom.features.home.league.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.league.domain.model.LeagueDto
import com.example.sportwisdom.features.home.league.domain.state.LeagueState
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LeagueReducer @Inject constructor() : Reducer<LeagueState, com.example.sportwisdom.common.utils.BaseAction<*>> {

  override fun invoke(currentState: LeagueState, action: com.example.sportwisdom.common.utils.BaseAction<*>): LeagueState {
    return when (action) {
      com.example.sportwisdom.common.utils.BaseAction.Executing           -> currentState.copy(syncState = LeagueSyncState.Loading)
      com.example.sportwisdom.common.utils.BaseAction.EmptyResult         -> currentState.copy(syncState = LeagueSyncState.Empty)
      is com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess<*> -> currentState.copy(leagueModel = action.value as List<LeagueDto>, syncState = LeagueSyncState.Content)
      is com.example.sportwisdom.common.utils.BaseAction.Failed           -> currentState.copy(syncState = LeagueSyncState.Message(action.reason))
      else                                                                -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}