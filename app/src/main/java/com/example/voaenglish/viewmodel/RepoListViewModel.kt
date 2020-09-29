package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.base.BaseViewModelKotlin
import com.example.voaenglish.model.Item
import com.example.voaenglish.model.Message
import com.example.voaenglish.model.Project
import com.example.voaenglish.model.RepoRepository

class RepoListViewModel : BaseViewModelKotlin() {

    val repoLiveLive = MutableLiveData<List<Item>>()

    val repoListInboxLive = MutableLiveData<List<Message>>()

    val repoListProjectLive = MutableLiveData<List<Project>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false

            if (isSuccess) {
                repoLiveLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

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

    override fun onCleared() {
        super.onCleared()
    }
}