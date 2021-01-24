package com.example.sportwisdom.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sportwisdom.CoroutineAndroidTestRule
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SportWisdomDaoTest {

  private lateinit var database: SportWisdomDatabase
  private lateinit var dao: SportWisdomDao

  @get:Rule
  val coroutinesRules = CoroutineAndroidTestRule()

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun createDb() {
    val context: Context = ApplicationProvider.getApplicationContext()
    database = Room
        .inMemoryDatabaseBuilder(context, SportWisdomDatabase::class.java)
        .allowMainThreadQueries() //Allowing main thread queries, just for testing
        .build()

    dao = database.sportWisdomDao()
  }

  @After
  fun closeDb() {
    database.close()
  }

  @Test
  fun getEvents() = runBlockingTest {
    //Given
    mockedEvents().toList().forEach { dao.insertEvent(it) }

    //When
    val allEvents = dao.getEvents().first() //getting the first flow emitted. Flow emits the entire query result on each change.

    //Then
    assertEquals(allEvents.size, 2)
  }

  @Test
  fun insertEvents() = runBlockingTest {
    //Given
    val emptyList = dao.getEvents().first()
    assertTrue(emptyList.isEmpty())

    //When
    mockedEvents().toList().forEach { dao.insertEvent(it) }

    //Then
    assertEquals(dao.getEvents().first().size, 2)
  }

  @Test
  fun deleteEventById() = runBlockingTest {
    //Given
    val id = "1"
    mockedEvents().toList().forEach { dao.insertEvent(it) }

    //When
    dao.deleteEventById(id)
    val result = dao.getEvents().first()

    //Then
    assertEquals(result.size, 1)
    assertTrue(result.none { it.idEvent == "1" })
  }

  @Test
  fun deleteAllEvents() = runBlockingTest {
    //Given
    mockedEvents().toList().forEach { dao.insertEvent(it) }
    assertEquals(dao.getEvents().first().size, 2)


    //When
    dao.deleteAllEvents()
    val result = dao.getEvents().first()

    //Then
    assertTrue(result.none())
  }

  private fun mockedEvents() = arrayListOf<EventDto>().apply {
    add(
        EventDto(
            idEvent = "1",
            idLeague = "22",
            strEvent = "Soccer",
            strLeague = "Premier",
            strThumb = "",
            strTimestamp = "",
            strVenue = "",
            dateEvent = "",
            strTime = ""
        )
    )
    add(
        EventDto(
            idEvent = "2",
            idLeague = "12",
            strEvent = "Basketball",
            strLeague = "NBA",
            strThumb = "",
            strTimestamp = "",
            strVenue = "",
            dateEvent = "",
            strTime = ""
        )
    )
  }
}


