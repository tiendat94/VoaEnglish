package com.example.voaenglish.di.component;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.voaenglish.ProjectViewModelFactory;
import com.example.voaenglish.network.GitHubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    @Singleton
    @Provides
    GitHubService provideGitHubService() {
        return new Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Singleton
    @Provides
    ProjectViewModelFactory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent
    ) {
        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }

}
