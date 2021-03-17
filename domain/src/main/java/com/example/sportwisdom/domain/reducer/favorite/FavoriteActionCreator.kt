package com.example.sportwisdom.domain.reducer.favorite

import com.example.redux.Action
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.repository.FavoriteRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class FavoriteActionCreator @Inject constructor(private val repository: FavoriteRepository) : Action<FavoriteIntents, BaseAction<*>> {

  override fun invoke(intents: FavoriteIntents): Flow<BaseAction<*>> {
    return flow {
      val result = when (intents) {
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