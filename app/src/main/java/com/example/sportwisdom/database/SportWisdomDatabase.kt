package com.example.sportwisdom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportwisdom.base.DateConverters
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.search.domain.model.TeamDto

@Database(entities = [EventDto::class, TeamDto::class], version = 2, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class SportWisdomDatabase : RoomDatabase(){

  abstract fun sportWisdomDao(): SportWisdomDao

  companion object{
    const val DATABASE_NAME: String = "sport_wisdom_db"
  }
}