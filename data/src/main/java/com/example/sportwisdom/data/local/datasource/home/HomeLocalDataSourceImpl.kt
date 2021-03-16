package com.example.sportwisdom.data.local.datasource.home

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseCacheResponseHandler
import com.example.sportwisdom.data.job.NotificationWorkManager
import com.example.sportwisdom.data.local.room.SportWisdomDao
import com.example.sportwisdom.data.utils.safeCacheCall
import com.example.sportwisdom.domain.model.EventDto
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit
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
      .addTag(eventDto.idEvent)
      .setInputData(data.build())
      .build()

    workManager.enqueue(work)
  }

  companion object {
    const val SCHEDULE_EXTRA_EVENT = "event_name_extra"
    const val SCHEDULE_EXTRA_EVENT_ID = "event_id_extra"
  }
}