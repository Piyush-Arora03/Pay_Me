package com.example.webview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val WebView=findViewById<WebView>(R.id.webview)
        web(WebView)



    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun web(a:WebView){
        a.webViewClient= WebViewClient()
        a.apply {
           a.settings.javaScriptEnabled=true
            a.settings.safeBrowsingEnabled=true
            loadUrl("https://developer.android.com/guide/components/intents-filters#kotlin")
        }


    }
}