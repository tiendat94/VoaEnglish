package com.example.voaenglish.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.voaenglish.R
import com.example.voaenglish.model.Data
import kotlinx.android.synthetic.main.row_item_data.view.*

class DeleteAdapter constructor(private val context: Context, private val countCheckBox: CountCheckBox) : RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder>() {

    var dataList: ArrayList<Data> = ArrayList()

    private var count: Int? = 0

    private val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(context, android.R.string.no, Toast.LENGTH_SHORT).show()
    }

    private fun deleteItemFromList(view: View, position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete")
        builder.setMessage("Delete Item ?")
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            dataList?.removeAt(position)
            notifyDataSetChanged()
        })
        builder.setNegativeButton(android.R.string.no, DialogInterface.OnClickListener { dialog, which -> negativeButtonClick })
        builder.show()
    }

    fun updateData(dataList: ArrayList<Data>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class DeleteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setup(data: Data?) {
            itemView?.txt_Name?.text = data?.name
            itemView?.textCount?.text = data?.count.toString()
            itemView?.chk_selected.setOnClickListener {

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_item_data, null)
        return DeleteViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteViewHolder, position: Int) {
        val currentData: Data = dataList[position]
        holder?.setup(dataList[position])
        holder?.itemView?.chk_selected.isChecked = dataList[position].isSelected!!
        holder?.itemView?.tag = dataList[position]
        holder?.itemView?.chk_selected.setOnCheckedChangeListener { buttonView, isChecked ->
            dataList[position].isSelected = isChecked
            if (isChecked) {
                if (countCheckBox != null) {
                    count = count?.plus(dataList[position].count!!)
                    Toast.makeText(context, count.toString(), Toast.LENGTH_LONG).show()
                    count?.let { countCheckBox.getCountCheckBox(it) }
                    dataList?.let { countCheckBox.getDataListCheckBox(dataList) }
                    currentData?.let { countCheckBox.onItemCheck(it) }
                }

                Toast.makeText(context, "checked", Toast.LENGTH_LONG).show()
            } else {
                if (countCheckBox != null) {
                    count = count?.minus(dataList[position].count!!)
                    count?.let { countCheckBox.getCountCheckBox(it) }
                    currentData?.let { countCheckBox.onItemUncheck(it) }
                }
                Toast.makeText(context, "unchecked", Toast.LENGTH_LONG).show()

            }

        }

        holder?.itemView?.btn_delete_unit.setOnClickListener {
            deleteItemFromList(it, position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface CountCheckBox {
        fun getCountCheckBox(count: Int)
        fun getDataListCheckBox(dataList: ArrayList<Data>)
        fun onItemCheck(data: Data)
        fun onItemUncheck(data: Data)
    }


}