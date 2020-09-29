package com.example.voaenglish.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.voaenglish.model.Message;
import com.example.voaenglish.model.Project;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ProjectRepository {

    private MutableLiveData<List<Project>> data = new MutableLiveData<>();

    private MutableLiveData<List<Message>> listMessage = new MutableLiveData<List<Message>>();
    private static ProjectRepository projectRepository;

    public static ProjectRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository;
    }

    public ProjectRepository() {
    }

    public MutableLiveData<List<Project>> getProjectList(String userId) {

        GitHubClient.getGitHubService().getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<Project> getProjectDetails(String userId, String projectName) {
        final MutableLiveData<Project> data = new MutableLiveData<>();
        GitHubClient.getGitHubService().getProjectDetails(userId, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    Log.d("url getProjectDetail", String.valueOf(call.request()));
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public MutableLiveData<List<Message>> getInboxList() {

        GitHubClient.getGitHubService().getInbox().enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    listMessage.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                listMessage.setValue(null);
            }
        });
        return listMessage;
    }

}
