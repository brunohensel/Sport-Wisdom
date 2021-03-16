package com.example.sportwisdom.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.model.TeamDto

@Database(entities = [EventDto::class, TeamDto::class], version = 2, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class SportWisdomDatabase : RoomDatabase(){

  abstract fun sportWisdomDao(): SportWisdomDao

  companion object{
    const val DATABASE_NAME: String = "sport_wisdom_db"
  }
}