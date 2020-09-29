package com.example.voaenglish

import android.os.Bundle
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.fragment.RepoListFragment

class MainActivityKotlin : BaseActivity() {

    private lateinit var repoListFragment: RepoListFragment


    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            repoListFragment = RepoListFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, repoListFragment)
                    .commitAllowingStateLoss()
        }
    }
}