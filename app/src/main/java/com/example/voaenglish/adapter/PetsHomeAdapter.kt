package com.example.voaenglish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.voaenglish.R
import com.example.voaenglish.model.PetsModel
import kotlinx.android.synthetic.main.item_viewpager_pets.view.*
import org.jetbrains.anko.imageResource

class PetsHomeAdapter(private val models: ArrayList<PetsModel>, private val context: Context) : PagerAdapter() {

    override fun getCount(): Int {
        return models?.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mView = LayoutInflater.from(context).inflate(R.layout.item_viewpager_pets, container, false)
        mView?.title?.text = models[position]?.title
        mView?.txt_description?.text = models[position]?.description
        mView?.image?.imageResource = models[position]?.image
        container.addView(mView, 0)
        return mView
    }


}