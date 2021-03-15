package com.example.voaenglish.recyclerview

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.voaenglish.R
import com.example.voaenglish.utils.Constants
import kotlinx.android.synthetic.main.grid_item_row.view.*
import kotlinx.android.synthetic.main.progress_loading.view.*

class Items_GridViewAdapter(private var itemsCells: ArrayList<Int?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var mContext: Context

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(dataViews: ArrayList<Int?>) {
        this.itemsCells.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun addLoadingView() {
        //add loading item
        Handler().post {
            itemsCells.add(null)
            notifyItemInserted(itemsCells.size - 1)
        }
    }

    fun removeLoadingView() {
        // Remove loading item
        if (itemsCells.size != 0) {
            itemsCells.removeAt(itemsCells.size - 1)
            notifyItemRemoved(itemsCells.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        return if (viewType == Constants.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.grid_item_row, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(mContext).inflate(R.layout.progress_loading, parent, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                view.progressbar.indeterminateDrawable.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
            } else {
                view.progressbar.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constants.VIEW_TYPE_ITEM) {
            holder.itemView.grid_item_imageview.setBackgroundColor(itemsCells[position]!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsCells[position] == null) {
            Constants.VIEW_TYPE_LOADING
        } else {
            Constants.VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return itemsCells.size
    }
}