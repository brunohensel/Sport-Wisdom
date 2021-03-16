package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.utils.CoroutineTestRule
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.schedule.data.ScheduleRepository
import com.example.sportwisdom.utils.willReturn
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class ScheduleActionCreatorTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private val repository: ScheduleRepository = mock()
  private val actionCreator = ScheduleActionCreator(repository)

  @Test
  fun fetchCachedEvents_returnListOfEventDto_whenSuccess() = runBlockingTest {
    //Given
    val intent = ScheduleIntents.FetchCachedEvents
    val response = getEvents()
    repository.fetchCachedEvents() willReturn flowOf(BaseAction.RemoteSuccess(response))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.RemoteSuccess(response))
  }

  @Test
  fun fetchCachedEvents_returnFailed_whenError() = runBlockingTest {
    //Given
    val intent = ScheduleIntents.FetchCachedEvents
    val msg = "Unknown network error"
    whenever(repository.fetchCachedEvents()).thenReturn(flowOf(BaseAction.Failed(reason = msg)))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.Failed(reason = msg))
  }

  @Test
  fun deleteAllEvents_returnUnit_whenSuccess() = runBlockingTest {
    //Give
    val intent = ScheduleIntents.DeleteAllEvents
    repository.deleteAllEvents() willReturn flowOf(BaseAction.CacheSuccess(Unit))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.CacheSuccess(Unit))
  }

  @Test
  fun deleteEvent_returnInt_whenSuccess() = runBlockingTest {
    //Given
    val events = getEvents()
    val intent = ScheduleIntents.DeleteEvent(events.first().idEvent)
    repository.deleteEvent(events.first().idEvent) willReturn flowOf(BaseAction.CacheSuccess(2))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.CacheSuccess(2))
  }


  private fun getEvents() =
    arrayListOf<EventDto>().apply {
      for (id in 1..5) {
        add(
          EventDto(
            idEvent = id.toString(),
            idLeague = "",
            strEvent = "",
            strLeague = "",
            strThumb = "",
            strTimestamp = null,
            strVenue = null,
            dateEvent = "",
            strTime = "",
            dateTime = null
          )
        )
      }
    }

}