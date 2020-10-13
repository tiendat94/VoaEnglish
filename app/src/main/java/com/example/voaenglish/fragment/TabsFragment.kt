package com.example.voaenglish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.voaenglish.adapter.ViewPagerTabsAdapter
import com.example.voaenglish.databinding.FragmentTabsBinding
import kotlinx.android.synthetic.main.fragment_tabs.*

class TabsFragment : Fragment() {

    private val TAG: String? = TabsFragment::class.java.simpleName

    private lateinit var viewPagerTabsAdapter: ViewPagerTabsAdapter

    companion object {
        fun newInstance(position: Int?): TabsFragment {
            val instance = TabsFragment()
            val args = Bundle()
            args.putInt("position", position!!)
            instance.arguments = args
            return instance
        }
    }

    private lateinit var viewDataBinding: FragmentTabsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentTabsBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(viewLifecycleOwner)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayUseLogoEnabled(false)
            (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        }
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager(viewpager_tab_home)
        viewpager_tab_home?.setCurrentItem(0)
        tabs?.setupWithViewPager(viewpager_tab_home)
    }

    fun setupViewPager(viewPager: ViewPager) {
        val viewPagerTabsAdapter = ViewPagerTabsAdapter(activity!!.supportFragmentManager)
        viewPagerTabsAdapter?.addFragment(HomeFragment.newInstance(1), "Order Placed")
        viewPagerTabsAdapter?.addFragment(HomeFragment.newInstance(2), "Processing")
        viewPagerTabsAdapter?.addFragment(HomeFragment.newInstance(3), "Shipping")
        viewPagerTabsAdapter?.addFragment(HomeFragment.newInstance(4), "Delivered")
        viewPagerTabsAdapter?.addFragment(HomeFragment.newInstance(5), "Completed")
        viewPagerTabsAdapter?.addFragment(HomeFragment.newInstance(6), "Cancelled")
        viewpager_tab_home?.adapter = viewPagerTabsAdapter
    }
}