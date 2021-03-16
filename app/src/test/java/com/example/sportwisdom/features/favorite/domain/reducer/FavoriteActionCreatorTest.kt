package com.example.sportwisdom.features.favorite.domain.reducer

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.favorite.data.FavoriteRepository
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.utils.CoroutineTestRule
import com.example.sportwisdom.utils.willReturn
import com.google.common.truth.Truth
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
class FavoriteActionCreatorTest{
  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private val repository: FavoriteRepository = mock()
  private val actionCreator = FavoriteActionCreator(repository)

  @Test
  fun fetchCachedTeams_returnListOfEventDto_whenSuccess() = runBlockingTest {
    //Given
    val intent = FavoriteIntents.FetchCachedTeams
    val response = getTeams()
    repository.fetchCachedTeams() willReturn flowOf(BaseAction.RemoteSuccess(response))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    Truth.assertThat(result.count()).isEqualTo(2)
    Truth.assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Truth.assertThat(result.take(2).toList()).contains(BaseAction.RemoteSuccess(response))
  }

  @Test
  fun fetchCachedTeams_returnFailed_whenError() = runBlockingTest {
    //Given
    val intent = FavoriteIntents.FetchCachedTeams
    val msg = "Unknown network error"
    whenever(repository.fetchCachedTeams()).thenReturn(flowOf(BaseAction.Failed(reason = msg)))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    Truth.assertThat(result.count()).isEqualTo(2)
    Truth.assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Truth.assertThat(result.take(2).toList()).contains(BaseAction.Failed(reason = msg))
  }

  @Test
  fun deleteAllTeams_returnUnit_whenSuccess() = runBlockingTest {
    //Give
    val intent = FavoriteIntents.DeleteAllTeams
    repository.deleteAllTeams() willReturn flowOf(BaseAction.CacheSuccess(Unit))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    Truth.assertThat(result.count()).isEqualTo(2)
    Truth.assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Truth.assertThat(result.take(2).toList()).contains(BaseAction.CacheSuccess(Unit))
  }

  @Test
  fun deleteTeam_returnInt_whenSuccess() = runBlockingTest {
    //Given
    val teams = getTeams()
    val intent = FavoriteIntents.DeleteTeam(teams.first().idTeam)
    repository.deleteTeam(teams.first().idTeam) willReturn flowOf(BaseAction.CacheSuccess(2))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    Truth.assertThat(result.count()).isEqualTo(2)
    Truth.assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    Truth.assertThat(result.take(2).toList()).contains(BaseAction.CacheSuccess(2))
  }



  private fun getTeams() = arrayListOf<TeamDto>().apply {
    for (id in 1..5) {
      add(
        TeamDto(
          idTeam = "$id",
          strTeam = "",
          strAlternate = "",
          intFormedYear = "",
          strLeague = "",
          strStadium = "",
          strStadiumThumb = "",
          strStadiumDescription = "",
          strStadiumLocation = "",
          strDescriptionEN = "",
          strTeamBadge = "",
          strTeamJersey = "",
          strTeamLogo = "",
          strTeamBanner = ""
        )
      )
    }
  }
}