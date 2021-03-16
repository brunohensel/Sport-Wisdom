package com.example.sportwisdom.domain.reducer.favorite

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.TeamDto
import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState
import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavoriteReducer @Inject constructor(): Reducer<FavoriteState, BaseAction<*>> {

  override fun invoke(currentState: FavoriteState, action: BaseAction<*>): FavoriteState {
    return when(action){
      BaseAction.Executing       -> currentState.copy(syncState = FavoriteSyncState.Loading)
      BaseAction.EmptyResult     -> currentState.copy(syncState = FavoriteSyncState.Empty)
      is BaseAction.CacheSuccess -> currentState.copy(favoriteModel = action.value as Flow<List<TeamDto>>, syncState = FavoriteSyncState.Content)
      is BaseAction.SideEffect   -> currentState.copy(syncState = FavoriteSyncState.SideEffect)
      is BaseAction.Failed       -> currentState.copy(syncState = FavoriteSyncState.Message(action.reason))
      else                       -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}