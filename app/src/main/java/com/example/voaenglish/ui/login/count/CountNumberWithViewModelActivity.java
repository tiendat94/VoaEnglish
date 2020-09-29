package com.example.voaenglish.ui.login.count;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.voaenglish.R;
import com.example.voaenglish.base.BaseActivity;
import com.example.voaenglish.utils.IntentServiceResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CountNumberWithViewModelActivity extends BaseActivity implements LifecycleOwner, View.OnClickListener {

    private CountNumberViewModel countNumberViewModel;
    private TextView mTextScoreTeamA, mTextScoreTeamB;

    // private GmailListViewModel gmailListViewModel;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_count;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countNumberViewModel = ViewModelProviders.of(this).get(CountNumberViewModel.class);
        countNumberViewModel.init();
        initViews();
        registerLiveDataListener();

//        gmailListViewModel = ViewModelProviders.of(this).get(GmailListViewModel.class);
//        gmailListViewModel.init();
//
//        gmailListViewModel.getListMutableLiveData().observe(this, new Observer<List<Message>>() {
//            @Override
//            public void onChanged(List<Message> messages) {
//                Log.d("Message", messages.get(0).getPicture());
//                Toast.makeText(getApplicationContext(), messages.get(0).getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doThis(IntentServiceResult intentServiceResult) {
        Toast.makeText(this, intentServiceResult.getmResultValue(), Toast.LENGTH_LONG).show();
    }

    private void initViews() {
        mTextScoreTeamA = findViewById(R.id.text_score_a);
        mTextScoreTeamB = findViewById(R.id.text_score_b);
        findViewById(R.id.button_plus_a_1).setOnClickListener(this);
        findViewById(R.id.button_plus_a_2).setOnClickListener(this);
        findViewById(R.id.button_plus_a_3).setOnClickListener(this);
        findViewById(R.id.button_plus_b_1).setOnClickListener(this);
        findViewById(R.id.button_plus_b_2).setOnClickListener(this);
        findViewById(R.id.button_plus_b_3).setOnClickListener(this);
    }

    public void registerLiveDataListener() {
        countNumberViewModel.getmScoreTeamA().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mTextScoreTeamA.setText(String.valueOf(integer));
            }
        });

        countNumberViewModel.getmScoreTeamB().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mTextScoreTeamB.setText(String.valueOf(integer));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_plus_a_3:
                countNumberViewModel.increaseScoreTeamA(3);
                break;
            case R.id.button_plus_a_2:
                countNumberViewModel.increaseScoreTeamA(2);
                break;

            case R.id.button_plus_a_1:
                countNumberViewModel.increaseScoreTeamA(1);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }
}
