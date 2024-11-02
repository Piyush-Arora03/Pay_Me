package com.example.services

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    companion object{
        val Mytag="Mytag"
    }
    private lateinit var service : MusicPlayerServices
    private lateinit var reciver : BroadcastReceiver
    private lateinit var start : AppCompatButton
    private var isBound=false
    private lateinit var connection : ServiceConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text=findViewById<TextView>(R.id.textView)
        start=findViewById<AppCompatButton>(R.id.start)
        val pbar=findViewById<ProgressBar>(R.id.pbar)

        //set connections
         connection=object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(Mytag,"service connected")
              val binder=service as MusicPlayerServices.MusicPlayer
                this@MainActivity.service=binder.getservices()
                isBound=true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
              Log.d(Mytag,"service disconnected")
            }
        }
        Intent(this,MusicPlayerServices::class.java).also { it->
            bindService(it,connection, Context.BIND_AUTO_CREATE)
        }
        start.setOnClickListener {
            Music()
            pbar.visibility=ProgressBar.VISIBLE
        }


    reciver= object : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val data=intent?.getStringExtra("done")
        if(data=="done"){
            pbar.visibility=ProgressBar.INVISIBLE
            start.text="play"
        }
    }

}

//        pbar.visibility=ProgressBar.INVISIBLE
//        start.setOnClickListener {
//            pbar.visibility=ProgressBar.VISIBLE
//            val intent=Intent(this,MyService::class.java)
//            CoroutineScope(Dispatchers.Main).launch {
//                for(i in 100000..100005){
//                delay(4000)
//                intent.putExtra("n",i)
//                startService(intent)
//            }
//            }
//        }
//        val end=findViewById<AppCompatButton>(R.id.end)
//        end.setOnClickListener {
//            pbar.visibility=ProgressBar.INVISIBLE
//            stopService(Intent(this,MyService::class.java))
//        }

//        reciver=object : BroadcastReceiver(){
//            override fun onReceive(context: Context?, intent: Intent?) {
//                val data=intent?.getIntExtra(MyService.data,0)
//                val text=findViewById<TextView>(R.id.textView)
//                text.text="no of primes : "+data.toString()
//            }
//
//        }


    }
     fun Music(){
        if(isBound){
            if(service.isplaying()){
                service.pause()
                start.text="Play"
            }
            else{
                val intent=Intent(this,MusicPlayerServices::class.java)
                intent.action="com.example.MusicPlayerServices.START"
                startService(intent)
                service.play()
                start.text="Pause"
            }
        }
    }
    override fun onStart() {
        if(VERSION.SDK_INT>=VERSION_CODES.O){
        registerReceiver(reciver, IntentFilter(MusicPlayerServices.UPDATE), RECEIVER_NOT_EXPORTED)
        }
        else {
            registerReceiver(reciver, IntentFilter(MusicPlayerServices.UPDATE))
        }
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        if(isBound){
            unbindService(connection)
            isBound=false
        }
        unregisterReceiver(reciver)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}