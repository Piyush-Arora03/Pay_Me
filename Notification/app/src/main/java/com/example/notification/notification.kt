package com.example.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class NotificationApp : Application() {
    final public val channel1Id = "Channel_1"
    final public val channel2Id = "Channel_2"

    override fun onCreate()   {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the first notification channel
            val channel1 = NotificationChannel(
                channel1Id,
                "Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is channel 1"
            }

            // Create the second notification channel
            val channel2 = NotificationChannel(
                channel2Id,
                "Channel 2",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This is channel 2"
            }

            // Register the channels with the system
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
        }
    }
}
