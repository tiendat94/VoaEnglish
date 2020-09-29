package com.example.voaenglish.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.voaenglish.utils.IntentServiceResult;

import org.greenrobot.eventbus.EventBus;

public class MusicService extends IntentService {


    /**
     * @param name
     * @deprecated
     */
    public MusicService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        EventBus.getDefault().post(new IntentServiceResult(Activity.RESULT_OK, "done!!"));

    }
}
