package com.example.voaenglish.filter

import android.widget.Filter
import com.example.voaenglish.adapter.MessageAdapter
import com.example.voaenglish.model.Message

class FilterMessageSearch(adapter: MessageAdapter, messageList: ArrayList<Message>) : Filter() {

    val adapter: MessageAdapter? = adapter
    val messageList: ArrayList<Message>? = messageList
    val filteredList: ArrayList<Message>? = null


    override fun performFiltering(constraint: CharSequence?): FilterResults {
        filteredList?.clear()
        val results = FilterResults()
        if (constraint?.length == 0) {
            messageList?.let { filteredList?.addAll(it) }
        } else {
            val filterPattern = constraint?.toString()?.toLowerCase()?.trim()
            for (message in messageList!!) {
                if (constraint?.let { message?.message?.contains(it) }!!) {
                    filteredList?.add(message)
                } else if (constraint?.let { message?.subject?.contains(it) }!!) {
                    filteredList?.add(message)
                }
            }
        }

        results.values = filteredList
        results.count = filteredList?.size!!
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter?.notifyDataSetChanged()
    }
}