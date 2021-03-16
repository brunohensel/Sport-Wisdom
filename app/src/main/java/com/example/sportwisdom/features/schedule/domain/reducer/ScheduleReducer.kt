package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.schedule.domain.state.ScheduleState
import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ScheduleReducer @Inject constructor() : Reducer<ScheduleState, com.example.sportwisdom.common.utils.BaseAction<*>> {
  override fun invoke(currentState: ScheduleState, action: com.example.sportwisdom.common.utils.BaseAction<*>): ScheduleState {
    return when (action) {
      com.example.sportwisdom.common.utils.BaseAction.Executing          -> currentState.copy(syncState = ScheduleSyncState.Loading)
      com.example.sportwisdom.common.utils.BaseAction.EmptyResult        -> currentState.copy(syncState = ScheduleSyncState.Empty)
      is com.example.sportwisdom.common.utils.BaseAction.CacheSuccess<*> -> currentState.copy(scheduleModel = action.value as Flow<List<EventDto>>, syncState = ScheduleSyncState.Content)
      is com.example.sportwisdom.common.utils.BaseAction.Failed          -> currentState.copy(syncState = ScheduleSyncState.Message(action.reason))
      is com.example.sportwisdom.common.utils.BaseAction.SideEffect      -> currentState.copy(syncState = ScheduleSyncState.SideEffect)
      else                                                               -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}