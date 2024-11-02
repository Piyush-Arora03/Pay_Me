package com.example.fcm

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat

class app : Application() {
    companion object {
        val CHANNEL_ID = "channel_1"
    }
    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(CHANNEL_ID,"channel 1",NotificationManager.IMPORTANCE_HIGH)
                .apply {
                    description="this is channel 1"
                }
            val manager=getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

    }
}