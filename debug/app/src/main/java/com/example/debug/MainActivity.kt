package com.example.debug

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.debug.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "com.example.multiscreenapp.MainActivity.KEY"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Set up window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Use binding to find the button and EditTexts
        binding.button.setOnClickListener {
            val item1 = binding.item1.text.toString()
            val item2 = binding.item2.text.toString()
            val item3 = binding.item3.text.toString()
            val item4 = binding.item4.text.toString()

            // Concatenate the values properly
            val order = item1 + item2 + item3 + item4  // Optional: format for clarity

            val intent = Intent(this, OrderPlaced::class.java)
            intent.putExtra(KEY, order)
            startActivity(intent)
            }
        }
}