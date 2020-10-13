package com.example.voaenglish

import android.app.Application
import android.widget.Toast
import com.example.voaenglish.callback.AppLifecyclerHandler
import com.example.voaenglish.callback.LifecycleDelete
import com.facebook.FacebookSdk
import com.google.android.gms.ads.MobileAds

class VoaAppKolin : Application(), LifecycleDelete {
    private lateinit var appLifecyclerHandler: AppLifecyclerHandler

    override fun onCreate() {
        super.onCreate()
        instance = this
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
        Toast.makeText(applicationContext, "onAppBackground", Toast.LENGTH_LONG).show()
    }

    override fun onAppForeground() {
        Toast.makeText(applicationContext, "onAppForeground", Toast.LENGTH_LONG).show()
    }

}