package com.example.voaenglish.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver(private val context: Context?) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        when (intent?.action) {

        }

        StringBuilder().apply {
            append("Action : ${intent?.action}\n")
            append("URI : ${intent?.toUri(Intent.URI_INTENT_SCHEME)}")
            toString().also {
                Log.d(TAG, it)
                //Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}