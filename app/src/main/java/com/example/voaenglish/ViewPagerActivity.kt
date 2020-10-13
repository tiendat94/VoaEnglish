package com.example.voaenglish

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.voaenglish.adapter.ViewPagerHomeAdapter
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.broadcast.MyBroadcastReceiver
import com.example.voaenglish.databinding.ActivityViewPagerBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : BaseActivity() {

    private lateinit var activityViewPagerBinding: ActivityViewPagerBinding
    private lateinit var viewPagerHomeAdapter: ViewPagerHomeAdapter
    private lateinit var broadcastReceiver: MyBroadcastReceiver
    private lateinit var wifiManager: WifiManager
    private val TAG: String? = ViewPagerActivity::class.java.simpleName

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }

    private val wifiStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    wifiSwitch.isChecked = true
                    wifiSwitch.text = "Wifi is ON"
                    Toast.makeText(this@ViewPagerActivity, "Wifi is On", Toast.LENGTH_LONG).show()

                }
                WifiManager.WIFI_STATE_DISABLED -> {
                    wifiSwitch.isChecked = false
                    wifiSwitch.text = "Wifi is OFF"
                    Toast.makeText(this@ViewPagerActivity, "Wifi is Off", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun openGallery() {
        var intent: Intent? = null
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        } else {
            intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.INTERNAL_CONTENT_URI)
        }

        intent?.setType("video/*")
        intent?.setAction(Intent.ACTION_GET_CONTENT)
        intent?.putExtra("return-data", true)
        intent?.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(intent, 100)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewPagerBinding = DataBindingUtil.setContentView(this@ViewPagerActivity, R.layout.activity_view_pager)
        supportActionBar!!.hide()
        initToolbar()
        viewPagerHomeAdapter = ViewPagerHomeAdapter(supportFragmentManager, lifecycle)
        view_pager.adapter = viewPagerHomeAdapter

        //WIFI
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        //broadcast receiver
        broadcastReceiver = MyBroadcastReceiver(applicationContext)

        wifiSwitch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                wifiManager.isWifiEnabled = false
                wifiSwitch.text = "Wifi is ON"
            } else {
                wifiManager.isWifiEnabled = true
                wifiSwitch?.text = "Wifi is OFF"
            }
        }
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }
        registerReceiver(broadcastReceiver, filter)

        bottom_bar.setupWithViewPager2(view_pager)

        Log.d(TAG, "onCreate")
        checkPermission()

        //  DesignerToast.Success(this@ViewPagerActivity, "Success Toast", Gravity.BOTTOM, Toast.LENGTH_LONG)
        activityViewPagerBinding?.executePendingBindings()
    }

    private fun checkPermission() {
        Dexter.withActivity(this@ViewPagerActivity)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        openGallery()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {

                    }

                }).check()
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiStateReceiver, intentFilter)
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiStateReceiver)
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
        Log.d(TAG, "onDestroy")
    }

    private fun initToolbar() {
        toolbar?.setOnApplyWindowInsetsListener { v, insets ->
            toolbar.setPadding(
                    toolbar.paddingLeft,
                    toolbar.paddingTop + insets.systemWindowInsetTop,
                    toolbar.paddingRight,
                    toolbar.paddingBottom
            )
            insets.consumeSystemWindowInsets()
        }
    }


}


