package com.example.voaenglish.di.component;

import com.example.voaenglish.viewmodel.ProjectListViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    ProjectListViewModel projectListViewModel();

}
