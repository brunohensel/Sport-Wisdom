package com.example.sportwisdom.features.search.domain.reducer

import com.example.redux.Action
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.search.data.SearchRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class SearchActionCreator @Inject constructor(private val searchRepository: SearchRepository) : Action<SearchIntents, BaseAction<*>> {

  override fun invoke(intents: SearchIntents): Flow<BaseAction<*>> {
    return flow {
      val result = when(intents){
        is SearchIntents.SearchForTeamsByName -> searchRepository.searchTeams(intents.teamName)
        is SearchIntents.AddToFavorites       -> searchRepository.insertTeam(intents.team)
      }
      emit(result)
    }
      .flattenMerge()
      .onStart { emit(BaseAction.Executing) }
  }
}