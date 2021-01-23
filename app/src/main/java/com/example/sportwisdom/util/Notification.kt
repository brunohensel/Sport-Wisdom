package com.example.sportwisdom.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color.RED
import android.media.AudioAttributes.*
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build
import android.os.Build.VERSION_CODES.O
import androidx.core.app.NotificationCompat
import com.example.sportwisdom.BuildConfig
import com.example.sportwisdom.R

private const val NOTIFICATION_CHANNEL_ID = BuildConfig.APPLICATION_ID + ".channel"

object Notification {

  operator fun invoke(context: Context, event: String?, eventId: Int) {

    val notificationManager = context.applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= O && notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
      val name = context.getString(R.string.app_name)
      val ringtoneManager = getDefaultUri(TYPE_NOTIFICATION)
      val audioAttributes = Builder().setUsage(USAGE_NOTIFICATION_RINGTONE).setContentType(CONTENT_TYPE_SONIFICATION).build()
      val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, IMPORTANCE_HIGH).apply {
        enableLights(true)
        lightColor = RED
        setSound(ringtoneManager, audioAttributes)
      }
      notificationManager.createNotificationChannel(channel)
    }

    val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
      .setSmallIcon(R.mipmap.ic_launcher)
      .setContentTitle(context.getString(R.string.event_notification_title))
      .setContentText(event)
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .setAutoCancel(true)
      .build()

    notificationManager.notify(eventId, notification)
  }
}