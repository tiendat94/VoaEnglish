package com.example.voaenglish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.voaenglish.R
import com.example.voaenglish.model.FilterModel
import kotlinx.android.synthetic.main.item_checkbox.view.*

class CheckBoxAdapter() : RecyclerView.Adapter<CheckBoxAdapter.CheckboxHolder>() {

    var repoList: List<FilterModel> = emptyList()

    class CheckboxHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setUp(checkBox: FilterModel?) {
            itemView?.brand_name?.text = checkBox?.name
            if (checkBox?.isCheck!!) {
                itemView?.brand_select.isChecked = true
            } else {
                itemView?.brand_select?.isChecked = false
            }

            itemView?.brand_select.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    Toast.makeText(itemView.context,"selected brand is " + checkBox?.name.toString(),Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxHolder {
        val checkboxlist = LayoutInflater.from(parent.context).inflate(R.layout.item_checkbox, parent, false)
        return CheckboxHolder(checkboxlist)
    }

    override fun onBindViewHolder(holder: CheckboxHolder, position: Int) {
        holder?.setUp(repoList?.get(position))
    }

    override fun getItemCount(): Int {
        return repoList?.size
    }

    fun updateRepoList(repoList: List<FilterModel>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }

}