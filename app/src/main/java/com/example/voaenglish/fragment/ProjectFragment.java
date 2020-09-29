package com.example.voaenglish.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.voaenglish.R;
import com.example.voaenglish.databinding.FragmentProjectDetailsBinding;
import com.example.voaenglish.model.Project;
import com.example.voaenglish.viewmodel.ProjectViewModel;

public class ProjectFragment extends Fragment {

    private static final String KEY_PROJECT_ID = "project_id";
    private FragmentProjectDetailsBinding binding;
    private ProjectViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false);
        binding.setIsLoading(true);
        return (View) binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        viewModel.init();
        //viewModel.setProjectID(getArguments().getString(KEY_PROJECT_ID));
        binding.setProjectViewModel(viewModel);


        viewModel.getProjectLiveData().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(Project project) {
                if (project != null) {
                    binding.setIsLoading(false);
                    viewModel.setProject(project);
                }
            }
        });
    }


    /**
     * creates project fragment for specific project ID
     */
    public static ProjectFragment forProject(String projectID) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();

        args.putString(KEY_PROJECT_ID, projectID);
        fragment.setArguments(args);

        return fragment;
    }
}
