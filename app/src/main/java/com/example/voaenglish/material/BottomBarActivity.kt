package com.example.voaenglish.material

import android.os.Bundle
import android.util.TypedValue
import androidx.core.view.setPadding
import com.example.voaenglish.R
import com.example.voaenglish.base.BaseActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_bottom_bar_new.*

class BottomBarActivity : BaseActivity() {

    private val tagList: List<String>? = listOf("Electronics", "Office & stationery", "Cars", "Furniture", "Sport", "Pets", "classy Show")


    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_bottom_bar_new
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tagList?.let { setTag(it) }
    }

    fun setTag(tagList: List<String>) {
        for (tagName: String in tagList) {
            chip.setText(tagName)
            chip.isCloseIconEnabled = true
        }
    }
}