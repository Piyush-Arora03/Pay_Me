package com.example.splashscreennew

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.window.SplashScreen
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.type.content

class animatedsp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animatedsp)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Remove the OnPreDrawListener after delaying the splash screen for 5 seconds
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (viewModel.isReady) {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            content.invalidate() // Redraw the view
                        }
                    }, 5000) // Delay for 5000 milliseconds (5 seconds)
                    return false // Suspend drawing until the delay is over
                }
            })
    }
}