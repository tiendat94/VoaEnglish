package com.example.voaenglish.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerTabsAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val mfragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
    private val mFragmentTitleList: ArrayList<String> = ArrayList<String>()

    override fun getCount(): Int {
        return mfragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {

        }
        return mfragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }

    fun addFragment(fragment: Fragment?, title: String?) {
        mfragmentList.add(fragment!!)
        mFragmentTitleList.add(title!!)
    }
}