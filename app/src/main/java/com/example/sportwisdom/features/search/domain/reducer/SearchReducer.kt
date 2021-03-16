package com.example.sportwisdom.features.search.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.features.search.domain.state.SearchState
import com.example.sportwisdom.features.search.domain.state.SearchSyncState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SearchReducer @Inject constructor() : Reducer<SearchState, com.example.sportwisdom.common.utils.BaseAction<*>> {

  override fun invoke(currentState: SearchState, action: com.example.sportwisdom.common.utils.BaseAction<*>): SearchState {
    return when(action){
      com.example.sportwisdom.common.utils.BaseAction.Executing        -> currentState.copy(syncState = SearchSyncState.Loading)
      com.example.sportwisdom.common.utils.BaseAction.EmptyResult      -> currentState.copy(syncState = SearchSyncState.Empty)
      is com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess -> currentState.copy(teamsModel = action.value as List<TeamDto>, syncState = SearchSyncState.Content)
      is com.example.sportwisdom.common.utils.BaseAction.SideEffect    -> currentState.copy(syncState = SearchSyncState.SideEffect)
      is com.example.sportwisdom.common.utils.BaseAction.Failed        -> currentState.copy(syncState = SearchSyncState.Message(action.reason))
      else                                                             -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}