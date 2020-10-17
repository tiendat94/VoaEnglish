package com.example.voaenglish

import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voaenglish.adapter.FlagsAdapter
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.viewmodel.FlagViewModel
import kotlinx.android.synthetic.main.activity_flag.*
import java.util.*
import kotlin.collections.ArrayList

class FlagsActivity : BaseActivity() {

    private lateinit var adapter: FlagsAdapter
    private lateinit var viewModel: FlagViewModel

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_flag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        country_rv.layoutManager = LinearLayoutManager(this)
        country_rv.setHasFixedSize(true)
        country_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        viewModel = ViewModelProviders.of(this@FlagsActivity).get(FlagViewModel::class.java)
        viewModel?.getListOfCountries()
        viewModel?.flagLiveData?.observe(this@FlagsActivity, androidx.lifecycle.Observer {
            adapter = FlagsAdapter(it)
            country_rv.adapter = adapter
        })

    }
}