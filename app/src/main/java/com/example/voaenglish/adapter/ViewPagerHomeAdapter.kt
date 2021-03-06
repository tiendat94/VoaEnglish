package com.example.voaenglish.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.voaenglish.fragment.HomeFragment
import com.example.voaenglish.fragment.RepoListFragment

class ViewPagerHomeAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment.newInstance(position)
            1 -> return RepoListFragment.newInstance(position)
            2 -> return RepoListFragment.newInstance(position)
            3 -> return RepoListFragment.newInstance(position)
            else -> return RepoListFragment.newInstance(position)
        }
    }

}