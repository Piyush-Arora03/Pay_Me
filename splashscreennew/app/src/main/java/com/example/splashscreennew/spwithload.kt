package com.example.splashscreennew

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.concurrent.thread

class spwithload : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spwithload)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        heavytask()
    }
    private fun heavytask(){
        longoperation().execute()
    }
    private open inner class longoperation: AsyncTask<String?, Void?, String?>() {
        override fun doInBackground(vararg params: String?): String? {
            for(i in 0..5){
                try {
                    Thread.sleep(1000)
                }
                catch (e:InterruptedException){
                    Thread.interrupted()
                }
            }
            return "result"
        }

         override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            startActivity(Intent(this@spwithload,animatedsp::class.java))
        }
    }
}