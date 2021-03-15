package com.example.voaenglish.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voaenglish.R;
import com.example.voaenglish.callback.ProjectClickCallback;
import com.example.voaenglish.databinding.ProjectListItemBinding;
import com.example.voaenglish.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    public List<? extends Project> projectList;


    @Nullable
    private final ProjectClickCallback projectClickCallback;

    public ProjectAdapter(@Nullable ProjectClickCallback projectClickCallback) {
        this.projectClickCallback = projectClickCallback;

    }

    public void setProjectList(final List<? extends Project> projectList) {
        if (this.projectList == null) {
            this.projectList = projectList;
            notifyItemRangeInserted(0, projectList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.projectList.size();
                }

                @Override
                public int getNewListSize() {
                    return projectList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.projectList.get(oldItemPosition).id == projectList.get(newItemPosition).id;
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project project = projectList.get(newItemPosition);
                    Project old = projectList.get(oldItemPosition);
                    return project.id == old.id && Objects.equals(project.getGit_url(), old.git_url);
                }
            });
            this.projectList = projectList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.project_list_item,
                parent, false);
        binding.setCallback(projectClickCallback);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.binding.setProject(projectList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return projectList == null ? 0 : projectList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        final ProjectListItemBinding binding;

        public ProjectViewHolder(ProjectListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
