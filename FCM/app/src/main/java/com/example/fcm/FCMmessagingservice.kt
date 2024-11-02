package com.example.fcm

import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMmessagingservice : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if(message.notification!=null){
        val notification =NotificationCompat.Builder(this,app.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText(message.notification?.body)
            .setContentTitle(message.notification?.title)
            .build()
            val manager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1,notification)
        }

        if(message.data!=null){
            Log.d("data",message.data.toString())
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }
}