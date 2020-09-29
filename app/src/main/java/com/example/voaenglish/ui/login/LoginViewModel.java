package com.example.voaenglish.ui.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voaenglish.MainActivity;
import com.example.voaenglish.utils.CommonUtils;

public class LoginViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<String> currentName;
    private MutableLiveData<Integer> clickCountA;
    private MutableLiveData<Integer> clickCountB;

    private LoginNavigator loginNavigator;

    public LoginNavigator getLoginNavigator() {
        return loginNavigator;
    }

    public void setLoginNavigator(LoginNavigator loginNavigator) {
        this.loginNavigator = loginNavigator;
    }

    public void init() {
      currentName.setValue("Hello World!");
    }


    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    public boolean isEmailAndPasswordVaild(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }

        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void gotoMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
