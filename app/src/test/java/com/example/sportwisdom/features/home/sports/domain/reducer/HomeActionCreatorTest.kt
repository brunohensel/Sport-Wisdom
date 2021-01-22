package com.example.sportwisdom.features.home.sports.domain.reducer

import com.example.sportwisdom.CoroutineTestRule
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.sports.data.HomeRepository
import com.example.sportwisdom.features.home.sports.domain.model.LeagueDto
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
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
  fun fetchLeagues_returnMapOf_whenSuccess() = runBlockingTest {
    //Given
    val event = HomeEvent.FetchLeagues
    val map = mapOf("Soccer" to listOf<LeagueDto>())
    repository.fetchAllLeagues() willReturn flowOf(BaseAction.Success(map))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.Success(map))
  }

  @Test
  fun fetchLeagues_returnFailed_whenError() = runBlockingTest {
    //Given
    val event = HomeEvent.FetchLeagues
    whenever(repository.fetchAllLeagues()).thenReturn(flowOf(BaseAction.Failed(null, "Unknown network error")))

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
    val event = HomeEvent.FetchSports
    val response = SportsModel(emptyList())
    repository.fetchAllSports() willReturn flowOf(BaseAction.Success(response))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.Success(response))
  }

  @Test
  fun fetchSports_returnFailed_whenError() = runBlockingTest {
    //Given
    val event = HomeEvent.FetchSports
    whenever(repository.fetchAllSports()).thenReturn(flowOf(BaseAction.Failed(null, "Unknown network error")))

    //When
    val result = actionCreator.invoke(event)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Assert.assertTrue(result.take(2).toList().contains(BaseAction.Failed(null, "Unknown network error")))
  }
}

infix fun <T> T.willReturn(value: T) {
  given(this)
    .willReturn(value)
}