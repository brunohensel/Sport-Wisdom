package com.example.sportwisdom.domain.reducer.search

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.TeamDto
import com.example.sportwisdom.domain.repository.SearchRepository
import com.example.sportwisdom.utils.CoroutineTestRule
import com.example.sportwisdom.utils.willReturn
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
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
class SearchActionCreatorTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private val repository: SearchRepository = mock()
  private val actionCreator = SearchActionCreator(repository)

  @Test
  fun searchForTeamsByName_returnListOfTeamDto_whenSuccess() = runBlockingTest {
    //Given
    val response = getTeamsDto()
    val intent = SearchIntents.SearchForTeamsByName("Arsenal")
    repository.searchTeams("Arsenal") willReturn flowOf(BaseAction.RemoteSuccess(response))

    //When
    val result = actionCreator.invoke(intent)

    //Then
    assertThat(result.count()).isEqualTo(2)
    assertThat(result.take(1).toList()).contains(BaseAction.Executing)
    assertThat(result.take(2).toList()).contains(BaseAction.RemoteSuccess(response))
  }

  private fun getTeamsDto() = arrayListOf<TeamDto>().apply {
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