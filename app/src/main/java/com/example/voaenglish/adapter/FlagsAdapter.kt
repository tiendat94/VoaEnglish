package com.example.voaenglish.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.voaenglish.R
import kotlinx.android.synthetic.main.flag_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class FlagsAdapter(private var countryList: ArrayList<String>) : RecyclerView.Adapter<FlagsAdapter.CountryHolder>(), Filterable {

    private var countryFilterList = ArrayList<String>()
    private lateinit var context: Context

    init {
        countryFilterList = countryList
    }

    class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setUp(nameCountry: String) {
            itemView?.select_country_text.text = nameCountry
            itemView?.select_country_container.setBackgroundColor(Color.TRANSPARENT)
        }
    }


    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = countryList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in countryList) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder?.setUp(countryFilterList.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val countryListView = LayoutInflater.from(parent.context).inflate(R.layout.flag_row, parent, false)
        return CountryHolder(countryListView)
    }

}