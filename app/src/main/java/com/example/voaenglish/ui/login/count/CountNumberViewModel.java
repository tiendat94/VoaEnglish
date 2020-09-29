package com.example.voaenglish.ui.login.count;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountNumberViewModel extends ViewModel {

    private MutableLiveData<Integer> mScoreTeamA = new MutableLiveData<>();
    private MutableLiveData<Integer> mScoreTeamB = new MutableLiveData<>();

    public void init() {
        mScoreTeamA.setValue(0);
        mScoreTeamB.setValue(0);

    }

    public MutableLiveData<Integer> getmScoreTeamA() {
        return mScoreTeamA;
    }

    public MutableLiveData<Integer> getmScoreTeamB() {
        return mScoreTeamB;
    }

    public void increaseScoreTeamA(int score) {
        mScoreTeamA.setValue(mScoreTeamA.getValue() + score);
    }

    public void increaseScoreTeamB(int score) {
        mScoreTeamB.setValue(mScoreTeamB.getValue() + score);
    }


}
