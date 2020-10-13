package com.example.voaenglish.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class SongService : Service() {

    var player: MediaPlayer? = null
    private val url = "https://www.nhaccuatui.com/bai-hat/rss-graveyard.qskdVADCwj.html"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player?.apply {
            setAudioAttributes(
                    AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            )
            setDataSource(url)
            prepare()
            start()
        }
    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player?.start()
        Toast.makeText(applicationContext,"song music",Toast.LENGTH_LONG).show()
        return 1
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }

    override fun onLowMemory() {
        super.onLowMemory()

    }
}