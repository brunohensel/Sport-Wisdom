package com.example.sportwisdom.features.home.domain

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.data.HomeRepository
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.league.domain.model.LeagueDto
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.utils.CoroutineTestRule
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
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


@FlowPreview
@ExperimentalCoroutinesApi
class HomeActionCreatorTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private val repository: HomeRepository = mock()
  private val actionCreator = HomeActionCreator(repository)

  @Test
  fun fetchLeagues_returnListOfLeagueDto_whenSuccess() = runBlockingTest {
    //Given
    val sportType = "Soccer"
    val event = HomeIntents.FetchLeagues(sportType)
    val leagues = arrayListOf<LeagueDto>().apply {
      add(LeagueDto("1", "A", "Soccer"))
      add(LeagueDto("2", "B", "Baseball"))
    }
    val response = leagues.filter { it.sportType == sportType }
    repository.fetchAllLeagues(sportType) willReturn flowOf(BaseAction.RemoteSuccess(response))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(response.first().sportType).isEqualTo(sportType)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.RemoteSuccess(response))
  }

  @Test
  fun fetchLeagues_returnFailed_whenError() = runBlockingTest {
    //Given
    val sportType = "soccer"
    val event = HomeIntents.FetchLeagues(sportType)
    whenever(repository.fetchAllLeagues(sportType)).thenReturn(flowOf(BaseAction.Failed(null, "Unknown network error")))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Assert.assertTrue(result.take(2).toList().contains(BaseAction.Failed(null, "Unknown network error")))
  }

  @Test
  fun fetchSports_returnSportModel_whenSuccess() = runBlockingTest {
    //Given
    val event = HomeIntents.FetchSports
    val response = SportsModel(emptyList())
    repository.fetchAllSports() willReturn flowOf(BaseAction.RemoteSuccess(response))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.RemoteSuccess(response))
  }

  @Test
  fun fetchSports_returnFailed_whenError() = runBlockingTest {
    //Given
    val event = HomeIntents.FetchSports
    whenever(repository.fetchAllSports()).thenReturn(flowOf(BaseAction.Failed(null, "Unknown network error")))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Assert.assertTrue(result.take(2).toList().contains(BaseAction.Failed(null, "Unknown network error")))
  }

  @Test
  fun fetchEvents_returnListOfEvents_whenSuccess() = runBlockingTest {
    //Given
    val leagueId = 4328
    val event = HomeIntents.FetchEvents(leagueId)
    val response = listOf<EventDto>()
    repository.fetchEvents(leagueId) willReturn flowOf(BaseAction.RemoteSuccess(response))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.RemoteSuccess(response))
  }

  @Test
  fun fetchEvents_returnFailed_whenError() = runBlockingTest {
    //Given
    val leagueId = 4328
    val event = HomeIntents.FetchEvents(leagueId)
    whenever(repository.fetchEvents(leagueId)).thenReturn(flowOf(BaseAction.Failed(null, "Unknown network error")))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Assert.assertTrue(result.take(2).toList().contains(BaseAction.Failed(null, "Unknown network error")))
  }

  @Test
  fun insertEvent_returnCacheSuccess() = runBlockingTest {
    //Given
    val eventDto = EventDto(
      idEvent = "1",
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

    val intent = HomeIntents.InsertEvent(eventDto)
    repository.insertEvent(eventDto) willReturn flowOf(BaseAction.CacheSuccess(1L))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.CacheSuccess(1L))
  }
}