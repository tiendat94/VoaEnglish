package com.example.voaenglish.ui.login.recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voaenglish.R
import com.example.voaenglish.base.BaseActivity
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : BaseActivity() {

    private lateinit var adapter: RecyclerViewAdapter

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_recyclerview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
    }

    private fun getData(): List<DataItem> {
        var dataList = ArrayList<DataItem>()

        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_ONE, "1. Hi! I am in View 1"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_TWO, "2. Hi! I am in View 2"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_ONE, "3. Hi! I am in View 3"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_TWO, "4. Hi! I am in View 4"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_ONE, "5. Hi! I am in View 5"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_TWO, "6. Hi! I am in View 6"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_ONE, "7. Hi! I am in View 7"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_TWO, "8. Hi! I am in View 8"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_ONE, "9. Hi! I am in View 9"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_TWO, "10. Hi! I am in View 10"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_ONE, "11. Hi! I am in View 11"))
        dataList.add(DataItem(RecyclerViewAdapter.VIEW_TYPE_TWO, "12. Hi! I am in View 12"))

        return dataList
    }

    private fun setupAdapter() {
        adapter = RecyclerViewAdapter(this@RecyclerViewActivity)
        adapter.updateData(getData())
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }
}