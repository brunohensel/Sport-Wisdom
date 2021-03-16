package com.example.sportwisdom.features.schedule.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.reducer.schedule.ScheduleIntents.DeleteEvent
import com.example.sportwisdom.domain.reducer.schedule.ScheduleIntents.FetchCachedEvents
import com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.*
import com.example.sportwisdom.features.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

  private val viewModel: ScheduleViewModel by viewModels()
  private val scheduleAdapter by lazy { ScheduleAdapter(::onDeleteItem) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(intents = flowOf(FetchCachedEvents)) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { scheduleState ->
          when (scheduleState.syncState) {
            Loading    -> progressBarCachedEvents.isVisible = true
            Content    -> displayEvents(scheduleState.scheduleModel)
            Empty      -> handleEmptyState()
            SideEffect -> progressBarCachedEvents.isVisible = false
            is Message -> displayErrorMessage((scheduleState.syncState as Message).msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvCachedEvents.adapter = scheduleAdapter
  }

  private fun displayEvents(eventsModel: Flow<List<EventDto>>) {
    progressBarCachedEvents.isVisible = false
    lifecycleScope.launch {
      eventsModel.collect { cachedEvents ->
        txtEmptyCachedEventsState.isVisible = cachedEvents.isEmpty()
         scheduleAdapter.submitList(cachedEvents)
      }
    }
  }

  private fun handleEmptyState() {
    txtEmptyCachedEventsState.isVisible = true
    progressBarCachedEvents.isVisible = false
  }

  private fun displayErrorMessage(msg: String?) {
    progressBarCachedEvents.isVisible = false
    Snackbar.make(requireView(), msg ?: "Unknown error", Snackbar.LENGTH_LONG).show()
  }

  private fun onDeleteItem(eventDto: EventDto) {
    lifecycleScope.launch {
      viewModel.process(intents = flowOf(DeleteEvent(eventDto.idEvent)))
    }
  }
}