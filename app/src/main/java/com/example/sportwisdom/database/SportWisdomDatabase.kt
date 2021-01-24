package com.example.sportwisdom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportwisdom.base.DateConverters
import com.example.sportwisdom.features.home.events.domain.model.EventDto

@Database(entities = [EventDto::class], version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class SportWisdomDatabase : RoomDatabase(){

  abstract fun sportWisdomDao(): SportWisdomDao

  companion object{
    const val DATABASE_NAME: String = "sport_wisdom_db"
  }
}