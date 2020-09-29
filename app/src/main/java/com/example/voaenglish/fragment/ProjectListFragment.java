package com.example.voaenglish.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.voaenglish.MainActivity;
import com.example.voaenglish.R;
import com.example.voaenglish.adapter.ProjectAdapter;
import com.example.voaenglish.callback.ProjectClickCallback;
import com.example.voaenglish.databinding.FragmentProjectListBinding;
import com.example.voaenglish.di.component.Injectable;
import com.example.voaenglish.model.Project;
import com.example.voaenglish.viewmodel.ProjectListViewModel;

import java.util.List;

public class ProjectListFragment extends Fragment implements Injectable {

    public static final String TAG = ProjectListFragment.class.getSimpleName();

    private FragmentProjectListBinding binding;
    private ProjectAdapter projectAdapter;

    private ProjectListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container
                , false);
        projectAdapter = new ProjectAdapter(projectClickCallback);
        binding.projectList.setAdapter(projectAdapter);
        binding.setIsLoading(true);
        return (View) binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        viewModel.init();
        observeViewModel(viewModel);
    }

    private void observeViewModel(ProjectListViewModel viewModel) {
        //Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                if (projects != null) {
                    binding.setIsLoading(false);
                    projectAdapter.setProjectList(projects);
                }
            }
        });
    }


    private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(project);
            }
        }
    };
}
