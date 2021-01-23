package com.example.sportwisdom.features.home.events.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@FlowPreview
@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

  private val viewModel: EventsViewModel by viewModels()
  private val args: EventsFragmentArgs by navArgs()
  private val eventsAdapter by lazy { EventsAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(flowOf(HomeEvent.FetchEvents(args.leagueId))) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { eventState ->
          when (eventState.syncState) {
            EventSyncState.Loading -> progressBarEvents.isVisible = true
            EventSyncState.Content -> displayEvents(eventState.eventsModel)
            EventSyncState.Empty   -> handleEmptyState()
            is EventSyncState.Message -> displayErrorMessage(eventState.syncState.msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  private fun handleEmptyState() {
    txtEmptyState.isVisible = true
    progressBarEvents.isVisible = false
  }

  private fun displayEvents(eventsModel: List<EventDto>) {
    progressBarEvents.isVisible = false
    txtEmptyState.isVisible = eventsModel.isEmpty()
    if (eventsModel.isNotEmpty()) eventsAdapter.submitList(eventsModel)
  }

  private fun displayErrorMessage(msg: String?) {
    progressBarEvents.isVisible = false
    Snackbar.make(requireView(), msg ?: "Unknown error", Snackbar.LENGTH_LONG).show()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvEvents.adapter = eventsAdapter
  }
}