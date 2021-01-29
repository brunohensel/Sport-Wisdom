package com.example.sportwisdom.features.favorite.domain.reducer

import com.example.redux.Action
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.favorite.data.FavoriteRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class FavoriteActionCreator @Inject constructor(private val repository: FavoriteRepository): Action<FavoriteIntents, BaseAction<*>> {

  override fun invoke(intents: FavoriteIntents): Flow<BaseAction<*>> {
    return flow {
      val result = when(intents){
        FavoriteIntents.FetchCachedTeams -> repository.fetchCachedTeams()
        FavoriteIntents.DeleteAllTeams   -> repository.deleteAllTeams()
        is FavoriteIntents.DeleteTeam    -> repository.deleteTeam(intents.teamId)
      }
      emit(result)
    }
      .flattenMerge()
      .onStart { emit(BaseAction.Executing) }
  }
}