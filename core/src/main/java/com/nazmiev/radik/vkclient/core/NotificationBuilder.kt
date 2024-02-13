package com.nazmiev.radik.vkclient.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
import androidx.work.ForegroundInfo

class NotificationBuilder {

    companion object {
        fun createNotification(
            context: Context,
            notificationChannelId: String,
            notificationTitle: String,
            progress: Int,
            notificationId: Int
        ): ForegroundInfo {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(context)
            }

            val notification = NotificationCompat.Builder(context, notificationChannelId)
                .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)
                .setContentTitle(notificationTitle)
                .setTicker(notificationTitle)
                .setSmallIcon(R.drawable.ic_image2vector)
                .setOngoing(true)
                .setProgress(100, progress, false)
                .build()

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ForegroundInfo(notificationId, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC)
            } else {
                ForegroundInfo(notificationId, notification)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun createChannel(context: Context) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("VKClient:Worker", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
