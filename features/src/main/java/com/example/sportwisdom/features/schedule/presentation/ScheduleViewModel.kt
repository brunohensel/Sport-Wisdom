package com.example.sportwisdom.features.schedule.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.domain.reducer.schedule.ScheduleActionCreator
import com.example.sportwisdom.domain.reducer.schedule.ScheduleIntents
import com.example.sportwisdom.domain.reducer.schedule.ScheduleReducer
import com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState
import com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf

@FlowPreview
class ScheduleViewModel @ViewModelInject constructor(
  reducer: ScheduleReducer,
  action: ScheduleActionCreator
) : BaseStateViewModel<ScheduleState, ScheduleIntents, BaseAction<*>>(
  initialState = ScheduleState(
    scheduleModel = flowOf(emptyList()),
    syncState = ScheduleSyncState.Content
  ),
  reducer = reducer,
  action = action
)