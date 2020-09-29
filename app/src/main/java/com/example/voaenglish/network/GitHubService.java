package com.example.voaenglish.network;

import com.example.voaenglish.model.GitHubRepo;
import com.example.voaenglish.model.Message;
import com.example.voaenglish.model.Project;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/starred")
    Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);

    String HTTPS_API_GITHUB_URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);

    @GET("https://api.androidhive.info/json/inbox.json")
    Call<List<Message>> getInbox();

}
