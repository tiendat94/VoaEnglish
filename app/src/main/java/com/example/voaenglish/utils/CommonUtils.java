package com.example.voaenglish.utils;

import android.content.Context;
import android.util.Patterns;
import android.view.View;

public final class CommonUtils {

    public CommonUtils() {

    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
