package com.example.voaenglish.ui.login.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.voaenglish.R
import kotlinx.android.synthetic.main.item_view_1.view.textView

class RecyclerViewAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val context: Context? = context
    private var list: List<DataItem> = emptyList()

    fun updateData(list: List<DataItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    private inner class View1ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(position: Int) {
            itemView.textView.text = list?.get(position)?.textData
        }
    }

    private inner class View2ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(position: Int) {
            itemView.textView.text = list?.get(position)?.textData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_1, parent, false))
        }
        return View2ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_2, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list!![position].viewType == VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

}