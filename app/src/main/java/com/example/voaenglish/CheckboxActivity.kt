package com.example.voaenglish

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.service.ExampleService
import com.example.voaenglish.service.ForegroundService
import kotlinx.android.synthetic.main.activity_check.*

class CheckboxActivity : BaseActivity(), ServiceConnection {
    var count: Int = 0

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_check
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ForegroundService.startService(this, "Foreground Service is running...")

        checkbox_one?.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedOrNot(isChecked)
        }

        checkbox_two?.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedOrNot(isChecked)
        }

        checkbox_three?.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedOrNot(isChecked)
        }

        checkbox_four?.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedOrNot(isChecked)
        }
        checkbox_five?.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedOrNot(isChecked)
        }

        button_count?.setOnClickListener {
            Toast.makeText(applicationContext, count.toString() + "checkbox checked", Toast.LENGTH_LONG).show()
        }
    }

    fun isCheckedOrNot(isChecked: Boolean) {
        if (isChecked) {
            count++
        } else {
            if (count > 0) {
                count--
            }
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val b: ExampleService.MyBinder = service as ExampleService.MyBinder

    }

    override fun onServiceDisconnected(name: ComponentName?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        ForegroundService.stopService(this)
    }
}