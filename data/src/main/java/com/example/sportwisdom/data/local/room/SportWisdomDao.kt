package com.example.sportwisdom.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

@Dao
interface SportWisdomDao {

  /** The [Long] return means which row the object was inserted to */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertEvent(eventDto: EventDto): Long

  @Query("SELECT * from events_table")
  fun getEvents(): Flow<List<EventDto>>

  @Query("DELETE from events_table WHERE idEvent = :id")
  suspend fun deleteEventById(id: String): Int

  @Query("DELETE from events_table ")
  suspend fun deleteAllEvents()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTeam(teamDto: TeamDto): Long

  @Query("SELECT * from teams_table")
  fun getTeams(): Flow<List<TeamDto>>

  @Query("DELETE from teams_table WHERE idTeam = :id")
  suspend fun deleteTeamById(id: String): Int

  @Query("DELETE from teams_table ")
  suspend fun deleteAllTeams()
}