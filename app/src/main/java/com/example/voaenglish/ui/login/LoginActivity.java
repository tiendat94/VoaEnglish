package com.example.voaenglish.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.voaenglish.R;
import com.example.voaenglish.base.BaseActivity;
import com.example.voaenglish.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity implements LoginNavigator, View.OnClickListener {

    private ActivityLoginBinding mActivityLoginBinding;

    private LoginViewModel loginViewModel;

    private TextView tvScoreA, tvScoreB;
    private Button btnPlayerA, btnPlayerB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();

        // Get the ViewModel
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mActivityLoginBinding.setViewModel(loginViewModel);
        mActivityLoginBinding.setLifecycleOwner(this);

        final Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        };

        loginViewModel.getCurrentName().observe(this, stringObserver);
//
//        tvScoreA.setText(String.valueOf(loginViewModel.getInitialCountA()));
//        tvScoreB.setText(String.valueOf(loginViewModel.getInitialCountB()));
    }

    private void initView() {
        tvScoreA = (TextView) findViewById(R.id.tvScorePlayerA);
        tvScoreB = (TextView) findViewById(R.id.tvScorePlayerB);
        btnPlayerA = (Button) findViewById(R.id.btnPlayerA);
        btnPlayerB = (Button) findViewById(R.id.btnPlayerB);
        btnPlayerA.setOnClickListener(this);
        btnPlayerB.setOnClickListener(this);
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void login() {

    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlayerA:
//                tvScoreA.setText(String.valueOf(loginViewModel.getCurrentCountA()));
                break;
            case R.id.btnPlayerB:
//                tvScoreB.setText(String.valueOf(loginViewModel.getCurrentCountB()));
                break;
            default:
        }
    }


}
