package com.example.voaenglish.ui.login.bottombar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.voaenglish.fragment.HomeFragment
import com.example.voaenglish.fragment.RepoListFragment
import com.example.voaenglish.fragment.TabsFragment

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment.newInstance(position)
            1 -> return TabsFragment.newInstance(position)
            2 -> return RepoListFragment.newInstance(position)
            3 -> return RepoListFragment.newInstance(position)
            else -> return RepoListFragment.newInstance(position)
        }
    }

}