package com.example.sportwisdom.features.schedule.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleIntents
import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@FlowPreview
@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

  private val viewModel: ScheduleViewModel by viewModels()
  private val scheduleAdapter by lazy { ScheduleAdapter(::onDeleteItem) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(intents = flowOf(ScheduleIntents.FetchCachedEvents)) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { scheduleState ->
          when (scheduleState.syncState) {
            ScheduleSyncState.Loading -> progressBarCachedEvents.isVisible = true
            ScheduleSyncState.Content -> displayEvents(scheduleState.scheduleModel)
            ScheduleSyncState.Empty -> handleEmptyState()
            ScheduleSyncState.SideEffect -> progressBarCachedEvents.isVisible = false
            is ScheduleSyncState.Message -> displayErrorMessage(scheduleState.syncState.msg)
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
        if (cachedEvents.isNotEmpty()) scheduleAdapter.submitList(cachedEvents)
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
      viewModel.process(intents = flowOf(ScheduleIntents.DeleteEvent(eventDto.idEvent)))
    }
  }
}