package com.example.voaenglish.ui.login.bottombar

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PowerManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.voaenglish.R
import com.example.voaenglish.base.BaseActivity
import com.fxn.OnBubbleClickListener
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_bottom_bar.*

class BottomBarActivity : BaseActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_bottom_bar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.e("height", "-> " + height)
        Log.e("width", "-> " + width)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        mediaPlayer = MediaPlayer().apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        }

        bubbleTabBar?.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.tab_home -> viewpager?.currentItem = 0
                    R.id.tab_friend -> viewpager?.currentItem = 1
                    R.id.tab_love -> viewpager?.currentItem = 2
                    R.id.tab_account -> viewpager?.currentItem = 3
                }
            }

        })
        bubbleTabBar?.setupBubbleTabBar(viewpager)
        viewpager.setDurationScroll(1000)
        viewpager?.adapter = ViewPagerAdapter(supportFragmentManager).apply {
        }


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}