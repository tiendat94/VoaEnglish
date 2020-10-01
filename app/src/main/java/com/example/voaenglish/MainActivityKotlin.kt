package com.example.voaenglish

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val inputMethodManager: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            if (inputMethodManager != null) {
                inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }
    }

}