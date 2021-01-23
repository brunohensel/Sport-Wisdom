package com.example.sportwisdom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

@Dao
interface SportWisdomDao {

  /** The [Long] return means which row the object was inserted to */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertEvent(eventDto: EventDto): Long

  @Query("SELECT * from events_table")
  suspend fun getEvents(): Flow<List<EventDto>>

  @Query("DELETE from events_table WHERE idEvent = :id")
  suspend fun deleteEventById(id: String): Int
}