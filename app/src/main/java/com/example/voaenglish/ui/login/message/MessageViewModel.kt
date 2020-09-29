package com.example.voaenglish.ui.login.message

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.base.BaseViewModelKotlin
import com.example.voaenglish.model.Message
import com.example.voaenglish.model.RepoRepository
import me.toptas.rssconverter.RssFeed

class MessageViewModel : BaseViewModelKotlin() {

    val repoListInboxLive = MutableLiveData<List<Message>>()
    val rssFeedLive = MutableLiveData<RssFeed>()

    fun fetListInbox() {
        dataLoading.value = true
        RepoRepository.getInstance().getInboxList { isSucces, response ->
            dataLoading.value = false
            if (isSucces) {
                repoListInboxLive.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

    fun fetRssFeed() {
        dataLoading.value = true
        RepoRepository.getInstance().getRssFeed { isSucces, response ->
            dataLoading.value = false
            if (isSucces) {
                rssFeedLive.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        repoListInboxLive.value = null
        rssFeedLive.value = null
    }

}