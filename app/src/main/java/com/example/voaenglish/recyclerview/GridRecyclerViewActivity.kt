package com.example.voaenglish.recyclerview

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voaenglish.R
import com.example.voaenglish.base.BaseActivity
import kotlinx.android.synthetic.main.activity_grid_recyclerview.*
import java.util.*
import kotlin.collections.ArrayList

class GridRecyclerViewActivity : BaseActivity() {

    private lateinit var itemsCells: ArrayList<Int?>
    private lateinit var loadMoreItemsCells: ArrayList<Int?>
    private lateinit var adapterGrid: Items_GridViewAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_grid_recyclerview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setItemsData()
        setAdapter()
        setRVLayoutManager()
        setRVScrollListener()
    }

    private fun setItemsData() {
        itemsCells = ArrayList()
        for (i in 0..41) {
            val random = Random()
            val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            itemsCells.add(color)
        }
    }

    private fun setAdapter() {
        adapterGrid = Items_GridViewAdapter(itemsCells)
        adapterGrid.notifyDataSetChanged()
        items_grid_rv.adapter = adapterGrid
    }

    private fun setRVLayoutManager() {
        mLayoutManager = GridLayoutManager(this, 3)
        items_grid_rv.layoutManager = mLayoutManager
        items_grid_rv.setHasFixedSize(true)
        items_grid_rv.adapter = adapterGrid
        (mLayoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapterGrid.getItemViewType(position)) {
                            com.example.voaenglish.utils.Constants.VIEW_TYPE_ITEM -> 1
                            com.example.voaenglish.utils.Constants.VIEW_TYPE_LOADING -> 3
                            else -> -1
                        }
                    }
                }
    }

    private fun setRVScrollListener() {
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as GridLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }

        })
        items_grid_rv.addOnScrollListener(scrollListener)
    }

    private fun loadMoreData() {
        adapterGrid.addLoadingView()
        //Create the loadMoreItemsCells ArrayList
        loadMoreItemsCells = ArrayList()
        val start = adapterGrid.itemCount
        val end = start + 16
        Handler().postDelayed({
            for (i in start..end) {
                val random = Random()
                val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                loadMoreItemsCells.add(color)
            }
            // Remove the loading view
            adapterGrid.removeLoadingView()
            adapterGrid.addData(loadMoreItemsCells)
            scrollListener.setLoaded()
            items_grid_rv.post {
                adapterGrid.notifyDataSetChanged()
            }
        }, 3000)
    }


}