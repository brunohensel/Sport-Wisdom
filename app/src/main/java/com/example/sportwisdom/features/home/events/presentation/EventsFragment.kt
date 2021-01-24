package com.example.sportwisdom.features.home.events.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.domain.HomeIntents
import com.example.sportwisdom.features.home.events.domain.model.EventDateDto
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.example.sportwisdom.util.formatTo
import com.example.sportwisdom.util.toDate
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import timber.log.Timber

@FlowPreview
@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

  private val viewModel: EventsViewModel by viewModels()
  private val args: EventsFragmentArgs by navArgs()
  private var eventDateDto = EventDateDto()
  private val eventsAdapter by lazy { EventsAdapter(::setSchedule) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(flowOf(HomeIntents.FetchEvents(args.leagueId))) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { eventState ->
          when (eventState.syncState) {
            EventSyncState.Loading -> progressBarEvents.isVisible = true
            EventSyncState.Content -> displayEvents(eventState.eventsModel)
            EventSyncState.Empty -> handleEmptyState()
            EventSyncState.Cache -> handleCache()
            is EventSyncState.Message -> displayErrorMessage(eventState.syncState.msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  private fun handleEmptyState() {
    txtEmptyState.isVisible = true
    progressBarEvents.isVisible = false
  }

  private fun handleCache() {
    progressBarEvents.isVisible = false
    Snackbar.make(requireView(), "Event saved", Snackbar.LENGTH_SHORT).show()
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

  private fun setSchedule(eventDto: EventDto) {
    Timber.i("Event: $eventDto")
    eventDto.setEventDateAndTime()?.let {
      eventDto.dateTime = it
      lifecycleScope.launch {
        Timber.i("EventProcess: $eventDto")
        viewModel.process(flowOf(HomeIntents.InsertEvent(eventDto)))
      }
    } ?: Snackbar.make(requireView(), "Time and Date invalid to create a notification", Snackbar.LENGTH_LONG).show()
  }

  private fun EventDto.setEventDateAndTime(): OffsetDateTime? {
    val year = dateEvent.take(4).takeWhile { it.isDigit() }.toInt()
    val month = dateEvent.substringAfter("-").take(2).toInt()
    val day = dateEvent.substringAfterLast("-").take(2).toInt()
    val hour = when {
      !strTimestamp.isNullOrBlank() -> strTimestamp.toDate()?.formatTo("HH")
      strTime != "00:00:00"         -> strTime.take(2)
      else                          -> null
    }
    val minute = when {
      !strTimestamp.isNullOrBlank() -> strTimestamp.toDate()?.formatTo("mm")
      strTime != "00:00:00"         -> strTime.substring(3..5).takeWhile { it.isDigit() }
      else                          -> null
    }
    eventDateDto = eventDateDto.copy(year = year, month = month, day = day, hour = hour?.toInt(), minute = minute?.toInt())

    return eventDateDto.takeIf { it.isTimeNotNull() }?.let {
      OffsetDateTime.of(
        it.year,
        it.month,
        it.day,
        it.hour!!,
        it.minute!!,
        0,
        0,
        OffsetDateTime.now().offset
      )
    }
  }

  companion object {
    const val SCHEDULE_EXTRA_EVENT = "event_name_extra"
    const val SCHEDULE_EXTRA_EVENT_ID = "event_id_extra"
  }
}