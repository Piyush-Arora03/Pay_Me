package com.example.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
      val mywebview=findViewById<WebView>(R.id.webview)
        mywebview.loadUrl("https://developer.android.com/develop/ui/views/layout/webapps/webview?authuser=2#AddingWebView")
        mywebview.settings.javaScriptEnabled=true
        mywebview.webViewClient= WebViewClient()
        onBackPressedDispatcher.addCallback {
            if (mywebview.canGoBack()) {
                mywebview.goBack()
            }
        }
        mywebview.settings.setSupportMultipleWindows(true)
    }
}