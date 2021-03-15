package com.example.voaenglish.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class ExampleService : Service() {
    private var startMode: Int = 0
    private var binder: IBinder? = null
    private var allowRebind: Boolean = false
    private var resultList: ArrayList<String> = ArrayList()
    private var counter: Int = 1

    public fun getWordList(): ArrayList<String> {
        return resultList
    }

    private fun addResultValues() {
        val random = Random()
        var input: List<String> = listOf("Linux", "Android", "iphone", "windows7")
        resultList?.add(input.get(random.nextInt(3)) + counter++)

    }

    public class MyBinder : Binder() {
        companion object {
            fun getService(): ExampleService {
                return ExampleService()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        // The service is being created
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // The service is starting, due to a call to startService()
        addResultValues()
        Toast.makeText(this, "service on", Toast.LENGTH_LONG)
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // A client is binding to the service with bindService()
        addResultValues()
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        // All clients have unbound with unbindService()

        return allowRebind
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        // A client is binding to the service with bindService()

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "service off", Toast.LENGTH_LONG)

    }
}