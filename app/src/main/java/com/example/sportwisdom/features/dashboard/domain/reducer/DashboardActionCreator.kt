package com.example.sportwisdom.features.dashboard.domain.reducer

import com.example.redux.Action
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.dashboard.data.DashboardRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class DashboardActionCreator @Inject constructor(private val repository: DashboardRepository) : Action<DashboardEvent, BaseAction> {

  override fun invoke(event: DashboardEvent): Flow<BaseAction> {
    return flow {
      val result = when (event) {
        DashboardEvent.fetchSports -> repository.fetchAllSports()
      }
      emit(result)
    }
      .flattenMerge()
      .onStart { emit(BaseAction.Executing) }
  }
}
