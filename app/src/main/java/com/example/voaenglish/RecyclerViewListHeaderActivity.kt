package com.example.voaenglish

import com.example.voaenglish.base.BaseActivity

class RecyclerViewListHeaderActivity : BaseActivity() {


    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_recyclerview_header
    }
}