package com.example.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MusicPlayerServices : Service(){
    lateinit var mplayer : MediaPlayer
    companion object{
        val channelid="channelid"
        val UPDATE="com.example.MusicPlayerServices.Update"
    }
    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
         mplayer=MediaPlayer.create(this,R.raw.wakhraswag)
        mplayer.setOnCompletionListener {
            val BroadcastIntent=Intent(UPDATE)
            BroadcastIntent.putExtra("done","done")
            sendBroadcast(BroadcastIntent)
            stopSelf()
        }
        createnotificationchannel()
    }

    private fun createnotificationchannel() {
        if(VERSION.SDK_INT>=VERSION_CODES.O) {
            val notificationchannel = NotificationChannel(
                channelid,
                "My Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationchannel)
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(MainActivity.Mytag,"start command")
        if (intent?.action == "com.example.MusicPlayerServices.PAUSE") {
            Log.d(MainActivity.Mytag,"pause")
            pause()
        } else if (intent?.action == "com.example.MusicPlayerServices.PLAY") {
            Log.d(MainActivity.Mytag,"Play")
            play()
        } else if (intent?.action == "com.example.MusicPlayerServices.STOP") {
            Log.d(MainActivity.Mytag,"stop")
            stopSelf()
            stopForeground(true)
            mplayer.stop()
            mplayer.release()
        }
        else{
            mplayer.start()
            shownotification()
        }
        return START_NOT_STICKY
    }

    private fun shownotification() {
        Log.d(MainActivity.Mytag,"show notification")
        val intent=Intent(this,MusicPlayerServices::class.java)
        intent.action="com.example.MusicPlayerServices.PAUSE"
        val pasueintent=PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val intent1=Intent(this,MusicPlayerServices::class.java)
        intent1.action="com.example.MusicPlayerServices.PLAY"
        val playintent=PendingIntent.getService(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT)

        val intent2=Intent(this,MusicPlayerServices::class.java)
        intent2.action="com.example.MusicPlayerServices.STOP"
        val stopintent=PendingIntent.getService(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT)


        val notification=NotificationCompat.Builder(this, channelid)
            .setContentText("music is playing")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("this is title")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_launcher_background,"pause",pasueintent)
            .addAction(R.drawable.ic_launcher_background,"play",playintent)
            .addAction(R.drawable.ic_launcher_background,"stop",stopintent)
            .build()
        startForeground(1,notification)
    }

    inner class MusicPlayer : Binder(){
        fun getservices() : MusicPlayerServices {
            Log.d(MainActivity.Mytag,"Music Player")
            //return an instance of this service so that client can call its methods
            return this@MusicPlayerServices
        }
    }
    override fun onBind(intent: Intent): IBinder {
        Log.d(MainActivity.Mytag,"On Bind")
      return MusicPlayer()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(MainActivity.Mytag,"UnBind")
        return true
    }

    override fun onRebind(intent: Intent?) {
        Log.d(MainActivity.Mytag,"ReBind")
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Log.d(MainActivity.Mytag,"On Destroy")
        super.onDestroy()
    }

    fun isplaying() :Boolean{
        return mplayer.isPlaying()
    }
    fun play(){
        return mplayer.start()
    }
    fun pause(){
        return mplayer.pause()
    }

}