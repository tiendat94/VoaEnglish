package com.example.voaenglish.utils;


import com.example.voaenglish.BuildConfig;

import timber.log.Timber;

public final class AppLogger {

    public AppLogger() {
    }

    public static void d(String s, Object... objects) {
        Timber.d(s, objects);
    }

    public static void d(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
