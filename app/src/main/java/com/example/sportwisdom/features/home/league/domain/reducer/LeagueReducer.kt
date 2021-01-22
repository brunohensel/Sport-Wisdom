package com.example.sportwisdom.features.home.league.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.league.domain.model.LeagueDto
import com.example.sportwisdom.features.home.league.domain.state.LeagueState
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LeagueReducer @Inject constructor() : Reducer<LeagueState, BaseAction> {

  override fun invoke(currentState: LeagueState, action: BaseAction): LeagueState {
    return when (action) {
      BaseAction.Executing     -> currentState.copy(syncState = LeagueSyncState.Loading)
      is BaseAction.Success<*> -> currentState.copy(leagueModel = action.value as List<LeagueDto>, syncState = LeagueSyncState.Content)
      is BaseAction.Failed     -> currentState.copy(syncState = LeagueSyncState.Message(action.reason))
    }
  }
}