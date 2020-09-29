package com.example.voaenglish.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voaenglish.model.Project;
import com.example.voaenglish.network.ProjectRepository;

import java.util.List;

public class ProjectListViewModel extends ViewModel {

    private MutableLiveData<List<Project>> mutableLiveData;
    ProjectRepository projectRepository;


    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        projectRepository = ProjectRepository.getInstance();
        mutableLiveData = projectRepository.getProjectList("Google");
    }

    public LiveData<List<Project>> getProjectListObservable() {
        return mutableLiveData;
    }
}
