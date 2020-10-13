package com.example.voaenglish.media

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

private const val ACTION_PLAY: String = "com.example.action.PLAY"

class MediaService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private var mMediaPlayer: MediaPlayer? = null

    fun initMediaPlayer() {
        mMediaPlayer?.setOnErrorListener(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        val action: String? = intent?.action

        when (action) {
            ACTION_PLAY -> {

            }
            else -> null
        }
        return null
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mMediaPlayer?.start()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false
    }
}