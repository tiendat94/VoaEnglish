package com.example.voaenglish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.voaenglish.databinding.ImageSliderLayoutItemBinding
import com.example.voaenglish.model.SliderItem
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.image_slider_layout_item.view.*

class SliderImageAdapter(private val context: Context) : SliderViewAdapter<SliderImageAdapter.SliderAdapterViewHolder>() {

    var slideImageList: List<SliderItem> = emptyList()

    class SliderAdapterViewHolder constructor(private val dataBinding: ViewDataBinding) : SliderViewAdapter.ViewHolder(dataBinding.root) {

        fun setup(sliderItem: SliderItem?) {

            Glide.with(itemView.context)
                    .load(sliderItem?.imageUrl)
                    .fitCenter()
                    .into(itemView.iv_auto_image_slider)

            dataBinding?.executePendingBindings()
            itemView.setOnClickListener {

            }
        }
    }


    override fun onBindViewHolder(holder: SliderAdapterViewHolder, position: Int) {
        holder?.setup(slideImageList[position])
    }

    fun updateListImageSlider(sliderItemLists: List<SliderItem>?) {
        if (sliderItemLists != null) {
            this.slideImageList = sliderItemLists
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return slideImageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val dataBinding = ImageSliderLayoutItemBinding.inflate(inflater, parent, false)
        return SliderAdapterViewHolder(dataBinding)
    }
    

}