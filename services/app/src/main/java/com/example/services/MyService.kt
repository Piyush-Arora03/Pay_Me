package com.example.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.sqrt

class MyService : Service() {
    private val scope= CoroutineScope(Dispatchers.IO )
    private val MYtag="Mytag"
    private val Channelid="Mychannel"
    companion object{
        const val ACTION_DATA_UPDATE = "com.example.services.DATA_UPDATE"
    const val data="data"
    }
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this@MyService, "Service created", Toast.LENGTH_SHORT).show()
        createnotificationchannel()
        val notification=NotificationCompat.Builder(this,Channelid)
            .setContentTitle("this is title")
            .setContentText("this is text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        startForeground(1,notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(MYtag,"start command called $startId")
        scope.launch {
            val n= intent?.getIntExtra("n",0)
            if (n != null) {
                HelloServices(startId,n)
            }
        }
        return START_REDELIVER_INTENT

    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
    private suspend fun HelloServices(StartID:Int, n: Int){
        withContext(Dispatchers.IO) {
            try {
                Log.d(MYtag, "service started for n=$n")
                val upperLimit = n
                val primes = findPrimes(upperLimit)
                val BroadcastIntent = Intent(ACTION_DATA_UPDATE)
                BroadcastIntent.putExtra(data,primes)
                sendBroadcast(BroadcastIntent)
                Log.d(MYtag, "no of primes: $primes for n=$n")
            } catch (e: InterruptedException) {
                Log.d(MYtag, e.toString())
            } finally {
//                stopSelf(StartID)
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
        Log.d(MYtag,"service stopped")
        super.onDestroy()
    }

    private fun findPrimes(n : Int) : Int{
        var isprime= BooleanArray(n+1){true}
        isprime[0]=false
        isprime[1]=false
        for(i in 2..sqrt(n.toDouble()).toInt()){
           if(isprime[i]==true){
               for(j in i*i..n step i){
                   isprime[j]=false
               }
           }
        }
        var cnt=0
        for(i in 0..n){
            if(isprime[i]==true) cnt++
        }
        return cnt
    }

    private fun createnotificationchannel(){
       if(VERSION.SDK_INT>=VERSION_CODES.O){
           val service=NotificationChannel(Channelid,"My Notification", NotificationManager.IMPORTANCE_DEFAULT)
           val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
           notificationManager.createNotificationChannel(service)
       }
    }
}