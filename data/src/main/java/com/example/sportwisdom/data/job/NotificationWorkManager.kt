package com.example.sportwisdom.data.job

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.sportwisdom.data.local.datasource.home.HomeLocalDataSourceImpl.Companion.SCHEDULE_EXTRA_EVENT
import com.example.sportwisdom.data.local.datasource.home.HomeLocalDataSourceImpl.Companion.SCHEDULE_EXTRA_EVENT_ID
import kotlinx.coroutines.FlowPreview

@FlowPreview
class NotificationWorkManager(private val context: Context, params: WorkerParameters) : Worker(context, params) {

  override fun doWork(): Result {
    val eventName = inputData.getString(SCHEDULE_EXTRA_EVENT)
    val eventId = inputData.getInt(SCHEDULE_EXTRA_EVENT_ID, 0)

    com.example.sportwisdom.data.utils.Notification(context, eventName, eventId)

    return Result.success()
  }
}