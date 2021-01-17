package com.example.sportwisdom.features.dashboard.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.dashboard.domain.model.League
import com.example.sportwisdom.features.dashboard.domain.model.SportsModel
import com.example.sportwisdom.features.dashboard.domain.state.DashboardState
import com.example.sportwisdom.features.dashboard.domain.state.DashboardSyncState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class DashboardReducer @Inject constructor() : Reducer<DashboardState, BaseAction> {

  override fun invoke(currentState: DashboardState, action: BaseAction): DashboardState {
    return when (action){
      BaseAction.Executing     -> currentState.copy(syncState = DashboardSyncState.Loading)
      is BaseAction.Success<*> -> currentState.copy(sportsModel = action.value as Map<String, List<League>>, syncState = DashboardSyncState.Content)
      is BaseAction.Failed     -> currentState.copy(syncState = DashboardSyncState.Message(action.reason))
    }
  }
}