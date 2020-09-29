package com.example.voaenglish.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voaenglish.model.Project;
import com.example.voaenglish.network.ProjectRepository;

public class ProjectViewModel extends ViewModel {

    private ProjectRepository projectRepository;
    private LiveData<Project> projectLiveData;
    private MutableLiveData<String> projectID;

    public ObservableField<Project> project = new ObservableField<>();


    public void init() {
        if (projectLiveData != null) {
            return;
        }
        projectRepository = ProjectRepository.getInstance();
        projectLiveData = projectRepository.getProjectDetails("Google", "0x0g-2018-badge");
    }

    public LiveData<Project> getProjectLiveData() {
        return projectLiveData;
    }

    public void setProjectLiveData(LiveData<Project> projectLiveData) {
        this.projectLiveData = projectLiveData;
    }

    public MutableLiveData<String> getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID.setValue(projectID);
    }

    public ObservableField<Project> getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }
}
