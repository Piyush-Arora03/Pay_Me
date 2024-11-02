package com.example.fcm

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private var Mytag="token"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener() {
            if(it.isSuccessful){
                Log.d(Mytag,it.result.toString())
                val text=findViewById<TextView>(R.id.test)
                text.text=it.result.toString()
                Toast.makeText(this,"token generated",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"token not generated",Toast.LENGTH_LONG).show()
            }
        }
    }
}