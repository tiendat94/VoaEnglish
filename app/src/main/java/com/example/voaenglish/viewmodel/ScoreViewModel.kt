package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.base.BaseViewModelKotlin
import org.jetbrains.anko.doAsync

class ScoreViewModel : BaseViewModelKotlin() {
    var scoreA: Int = 0
    var scoreB: Int = 0
    var score = MutableLiveData<Int>()


    fun incrementScore(isTeamA: Boolean) {
        if (isTeamA) {
            scoreA++
            score.value = scoreA
        } else {
            scoreB++
            score.value = scoreB
        }
        doAsync {

        }
    }
}