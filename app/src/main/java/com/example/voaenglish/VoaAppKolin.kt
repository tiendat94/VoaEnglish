package com.example.voaenglish

import android.app.Application
import android.widget.Toast
import com.example.voaenglish.callback.AppLifecyclerHandler
import com.example.voaenglish.callback.LifecycleDelete
import com.example.voaenglish.database.AppDatabase
import com.example.voaenglish.viewmodel.WordRepository
import com.facebook.FacebookSdk
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class VoaAppKolin : Application(), LifecycleDelete {
    private lateinit var appLifecyclerHandler: AppLifecyclerHandler

    override fun onCreate() {
        super.onCreate()
        instance = this
        val applicationScope = CoroutineScope(SupervisorJob())
        val database by lazy { AppDatabase.getInstance(this, applicationScope) }

        FacebookSdk.isInitialized()
        MobileAds.initialize(this)
//        Realm.init(applicationContext)
//        Realm.setDefaultConfiguration(RealmHelper.Companion.instance.getRealmConfig(applicationContext))
        appLifecyclerHandler = AppLifecyclerHandler(this)
        registerActivityLifecycleCallbacks(appLifecyclerHandler)
        registerComponentCallbacks(appLifecyclerHandler)
    }

    companion object {
        lateinit var instance: VoaAppKolin
    }

    override fun onAppBackground() {
       // Toast.makeText(applicationContext, "onAppBackground", Toast.LENGTH_LONG).show()
    }

    override fun onAppForeground() {
       // Toast.makeText(applicationContext, "onAppForeground", Toast.LENGTH_LONG).show()
    }

}