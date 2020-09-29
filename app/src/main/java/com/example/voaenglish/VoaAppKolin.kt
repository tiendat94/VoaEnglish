package com.example.voaenglish

import android.app.Application
import com.example.voaenglish.database.RealmHelper
import com.facebook.FacebookSdk
import io.realm.Realm

class VoaAppKolin : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        FacebookSdk.isInitialized()
        Realm.init(applicationContext)
        Realm.setDefaultConfiguration(RealmHelper.Companion.instance.getRealmConfig(applicationContext))
    }

    companion object {
        lateinit var instance: VoaAppKolin
    }

}