package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.base.BaseViewModelKotlin
import com.example.voaenglish.model.*

class RepoListViewModel : BaseViewModelKotlin() {

    val repoLiveLive = MutableLiveData<List<Item>>()

    val repoListInboxLive = MutableLiveData<List<Message>>()

    val repoListNewsLive = MutableLiveData<List<News>>()

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

    fun fetchNewsList() {
        dataLoading.value = true
        RepoRepository.getInstance().getNewList { isSucces, response ->
            dataLoading.value = false
            if (isSucces) {
                repoListNewsLive.value = response?.Data
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