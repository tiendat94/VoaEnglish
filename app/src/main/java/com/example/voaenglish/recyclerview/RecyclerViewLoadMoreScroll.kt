package com.example.voaenglish.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewLoadMoreScroll : RecyclerView.OnScrollListener {

    private var visibleThreshold = 5
    private lateinit var mOnLoadMoreListener: OnLoadMoreListener
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var mFlexboxLayoutManager: RecyclerView.LayoutManager

    fun setLoaded() {
        isLoading = false
    }

    fun getLoaded(): Boolean {
        return isLoading
    }

    fun setOnLoadMoreListener(moreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = moreListener
    }

    constructor(layoutManager: LinearLayoutManager) {
        this.mFlexboxLayoutManager = layoutManager
    }

    constructor(layoutManager: GridLayoutManager) {
        this.mFlexboxLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    constructor(layoutManager: StaggeredGridLayoutManager) {
        this.mFlexboxLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy <= 0) return
        totalItemCount = mFlexboxLayoutManager.itemCount
        if (mFlexboxLayoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions = (mFlexboxLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
            lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
        }else if (mFlexboxLayoutManager is GridLayoutManager){
            lastVisibleItem = (mFlexboxLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }

        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold){
            mOnLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

}