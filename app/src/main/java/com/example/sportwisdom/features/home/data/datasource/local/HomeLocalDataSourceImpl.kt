package com.example.sportwisdom.features.home.data.datasource.local

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseCacheResponseHandler
import com.example.sportwisdom.base.CacheResult
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.presentation.EventsFragment.Companion.SCHEDULE_EXTRA_EVENT
import com.example.sportwisdom.features.home.events.presentation.EventsFragment.Companion.SCHEDULE_EXTRA_EVENT_ID
import com.example.sportwisdom.job.NotificationWorkManager
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(private val dao: SportWisdomDao, private val workManager: WorkManager) : HomeLocalDataSource {

  @FlowPreview
  override suspend fun insertEvent(eventDto: EventDto): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.insertEvent(eventDto) }
    val cacheResponse = object : BaseCacheResponseHandler<Any, Long>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Long): BaseAction.CacheSuccess<Long> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
    createWorkManager(eventDto)
  }

  @FlowPreview
  private fun createWorkManager(eventDto: EventDto) {

    val timeTilFuture = ChronoUnit.MILLIS.between(OffsetDateTime.now(), eventDto.dateTime)
    val data = Data.Builder().apply {
      putString(SCHEDULE_EXTRA_EVENT, eventDto.strEvent)
      putInt(SCHEDULE_EXTRA_EVENT_ID, eventDto.idEvent.toInt())
    }

    val work = OneTimeWorkRequest
      .Builder(NotificationWorkManager::class.java)
      .setInitialDelay(timeTilFuture, TimeUnit.MILLISECONDS)
      .setInputData(data.build())
      .build()

    workManager.enqueue(work)
  }
}