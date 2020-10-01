package com.example.voaenglish

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.voaenglish.adapter.ViewPagerHomeAdapter
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.databinding.ActivityViewPagerBinding
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : BaseActivity() {

    private lateinit var activityViewPagerBinding: ActivityViewPagerBinding
    private lateinit var viewPagerHomeAdapter: ViewPagerHomeAdapter

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewPagerBinding = DataBindingUtil.setContentView(this@ViewPagerActivity, R.layout.activity_view_pager)
        supportActionBar!!.hide()
        initToolbar()
        viewPagerHomeAdapter = ViewPagerHomeAdapter(supportFragmentManager, lifecycle)
        view_pager.adapter = viewPagerHomeAdapter
        bottom_bar.setupWithViewPager2(view_pager)
        activityViewPagerBinding?.executePendingBindings()
    }

    private fun initToolbar() {
        toolbar?.setOnApplyWindowInsetsListener { v, insets ->
            toolbar.setPadding(
                    toolbar.paddingLeft,
                    toolbar.paddingTop + insets.systemWindowInsetTop,
                    toolbar.paddingRight,
                    toolbar.paddingBottom
            )
            insets.consumeSystemWindowInsets()
        }
    }

}


