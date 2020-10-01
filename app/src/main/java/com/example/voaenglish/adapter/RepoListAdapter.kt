package com.example.voaenglish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.voaenglish.BR
import com.example.voaenglish.RepoDetailActivity
import com.example.voaenglish.databinding.ViewRepoListItemBinding
import com.example.voaenglish.model.Item
import com.example.voaenglish.viewmodel.RepoListViewModel
import kotlinx.android.synthetic.main.view_repo_list_item.view.*

class RepoListAdapter(private val repoListViewModel: RepoListViewModel) : RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder>() {

    var repoList: List<Item> = emptyList()

    class RepoListViewHolder constructor(private val dataBinding: ViewDataBinding, private val repoListViewModel: RepoListViewModel) : RecyclerView.ViewHolder(dataBinding.root) {
        val avartarImage = itemView.item_avatar

        fun setup(itemData: Item) {
            dataBinding.setVariable(BR.itemData, itemData)
            dataBinding.executePendingBindings()

            Glide.with(itemView.context).load(itemData.owner.avatar_url).into(avartarImage)

            itemView.setOnClickListener {
                RepoDetailActivity.gotoActivityRepoDetail(itemView.context, itemData?.description, itemData?.html_url, itemData?.full_name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewRepoListItemBinding.inflate(inflater, parent, false)
        return RepoListViewHolder(dataBinding, repoListViewModel)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder?.setup(repoList[position])
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    fun updateRepoList(repoList: List<Item>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}