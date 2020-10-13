package com.example.voaenglish.callback

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle

class AppLifecyclerHandler(private var lifecycleDelete: LifecycleDelete, private var mIsAppInForeground: Boolean = false) : Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }


    override fun onActivityResumed(activity: Activity) {
        if (!mIsAppInForeground) {
            mIsAppInForeground = true
            lifecycleDelete.onAppForeground()
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    override fun onConfigurationChanged(newConfig: Configuration) {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            mIsAppInForeground = false
            lifecycleDelete.onAppBackground()
        }
    }
}