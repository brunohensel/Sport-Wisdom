package com.example.sportwisdom.features.search.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.features.search.domain.state.SearchState
import com.example.sportwisdom.features.search.domain.state.SearchSyncState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SearchReducer @Inject constructor() : Reducer<SearchState, BaseAction<*>> {

  override fun invoke(currentState: SearchState, action: BaseAction<*>): SearchState {
    return when(action){
      BaseAction.Executing        -> currentState.copy(syncState = SearchSyncState.Loading)
      BaseAction.EmptyResult      -> currentState.copy(syncState = SearchSyncState.Empty)
      is BaseAction.RemoteSuccess -> currentState.copy(teamsModel = action.value as List<TeamDto>, syncState = SearchSyncState.Content)
      is BaseAction.SideEffect    -> currentState.copy(syncState = SearchSyncState.SideEffect)
      is BaseAction.Failed        -> currentState.copy(syncState = SearchSyncState.Message(action.reason))
      else                        -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}