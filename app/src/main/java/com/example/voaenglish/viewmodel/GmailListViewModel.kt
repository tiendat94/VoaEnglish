package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.voaenglish.model.Message
import com.example.voaenglish.network.ProjectRepository

class GmailListViewModel : ViewModel() {
    var listMutableLiveData: MutableLiveData<List<Message>>? = null
        private set
    private var projectRepository: ProjectRepository? = null
    fun init() {
        if (listMutableLiveData != null) {
            return
        }
        projectRepository = ProjectRepository.getInstance()
        listMutableLiveData = projectRepository?.getInboxList()
    }
}