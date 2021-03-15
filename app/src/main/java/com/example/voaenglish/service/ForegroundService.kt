package com.example.voaenglish.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.voaenglish.CheckboxActivity
import com.example.voaenglish.R

class ForegroundService : Service() {
    private val CHANNEL_ID = "ForegroundService Kotlin"

    companion object {
        fun startService(context: Context?, message: String?) {
            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context!!, startIntent)
        }

        fun stopService(context: Context?) {
            val stopIntent = Intent(context, ForegroundService::class.java)
            context?.stopService(stopIntent)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, CheckboxActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service Kotlin Example")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_add)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1, notification)
        return START_NOT_STICKY
    }


}