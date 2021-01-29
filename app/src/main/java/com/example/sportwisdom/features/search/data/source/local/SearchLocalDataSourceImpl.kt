package com.example.sportwisdom.features.search.data.source.local

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.search.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

class SearchLocalDataSourceImpl(private val dao: SportWisdomDao) : SearchLocalDataSource {
  override suspend fun insertTeam(team: TeamDto): Flow<BaseAction<*>> {

  }
}