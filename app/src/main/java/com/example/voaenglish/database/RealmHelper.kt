package com.example.voaenglish.database

import android.content.Context
import com.example.voaenglish.utils.Constants
import io.realm.RealmConfiguration

class RealmHelper {
    companion object {
        val instance = RealmHelper()
    }

    var realmConfiguration: RealmConfiguration? = null
    var key: String? = null

    fun getRealmConfig(context: Context): RealmConfiguration? {
        if (realmConfiguration == null) {
            realmConfiguration = RealmConfiguration.Builder()
                    .name(Constants.DB_NAME)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }
        return realmConfiguration
    }
}