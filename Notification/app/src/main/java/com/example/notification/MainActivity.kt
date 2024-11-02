package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val channel1Id = "Channel_1"
    private val channel2Id = "Channel_2"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.high.setOnClickListener {
            handleNotification(channel1Id)
        }

        binding.low.setOnClickListener {
            handleNotification(channel2Id)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            createNotificationChannel()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermission(onPermissionGranted: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED -> {
                if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                    showDialog(onPermissionGranted)
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            else -> {
                createNotificationChannel()
                onPermissionGranted()
            }
        }
    }

    private fun showDialog(onPermissionGranted: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permission Required")
        builder.setMessage("We need notification permission to perform this action.")
        builder.setPositiveButton("OK") { _, _ ->
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)

            // Check and create channel 1
            if (manager.getNotificationChannel(channel1Id) == null) {
                val channel1 = NotificationChannel(
                    channel1Id,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "This is channel 1"
                }
                manager.createNotificationChannel(channel1)
            }

            // Check and create channel 2
            if (manager.getNotificationChannel(channel2Id) == null) {
                val channel2 = NotificationChannel(
                    channel2Id,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "This is channel 2"
                }
                manager.createNotificationChannel(channel2)
            }
        }
    }

    private fun handleNotification(channelId: String) {
        requestPermission {
            shownotification(channelId)
        }
    }

    private fun shownotification(channelId: String) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(binding.title.text.toString())
            .setContentText(binding.text.text.toString())
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(if (channelId == channel2Id) NotificationCompat.PRIORITY_LOW else NotificationCompat.PRIORITY_HIGH)
            .build()

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = if (channelId == channel1Id) 1 else 2
        manager.notify(notificationId, notification)
    }
}
