package com.example.sportwisdom.di

import android.content.Context
import androidx.room.Room
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.database.SportWisdomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): SportWisdomDatabase =
    Room
      .databaseBuilder(context, SportWisdomDatabase::class.java, SportWisdomDatabase.DATABASE_NAME)
      .fallbackToDestructiveMigration() //do not use this in a productive app
      .build()

  @Provides
  @Singleton
  fun provideSportWisdomDao(database: SportWisdomDatabase): SportWisdomDao = database.sportWisdomDao()
}