package com.example.voaenglish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.voaenglish.adapter.DeleteAdapter
import com.example.voaenglish.model.Data
import kotlinx.android.synthetic.main.activity_scrolling.*

class CollapsingActivity : AppCompatActivity() {

    private lateinit var deleteAdapter: DeleteAdapter
    private val item_list: ArrayList<Data> = emptyList<Data>() as ArrayList<Data>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setupAdapter()
    }

    private fun setupAdapter() {
        deleteAdapter = DeleteAdapter(this@CollapsingActivity)
        recyclerview?.adapter = deleteAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}