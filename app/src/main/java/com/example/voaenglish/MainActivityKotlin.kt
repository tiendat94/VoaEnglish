package com.example.voaenglish

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.fragment.HomeFragment
import com.example.voaenglish.fragment.TabsFragment
import com.example.voaenglish.model.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityKotlin : BaseActivity() {

    private lateinit var repoListFragment: TabsFragment
    private lateinit var homeFragment: HomeFragment
    private var jsonMessageObject: String? = null
    private var listMessage: ArrayList<com.example.voaenglish.model.Message> = ArrayList()


    companion object {
        fun start(context: Context, listMessage: String) {
            val intent = Intent(context, MainActivityKotlin::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("list", listMessage)
            context.startActivity(intent)
        }
    }


    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras != null) {
            jsonMessageObject = intent?.extras?.getString("list")
        }
        listMessage = Gson().fromJson<Any>(jsonMessageObject, object : TypeToken<List<Message?>?>() {}.type) as ArrayList<Message>
        Log.d("MainActivityKotlin", listMessage[0].message)
        Log.d("MainActivityKotlin", listMessage[0].subject)
        homeFragment = HomeFragment()
        if (savedInstanceState == null) {
            repoListFragment = TabsFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, repoListFragment)
                    .commitAllowingStateLoss()
        }

        fragmentA.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, repoListFragment)
                    .commitAllowingStateLoss()
        }

        fragmentB.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment)
                    .commitAllowingStateLoss()
        }

    }

    fun addAppWidget(data: Intent?) {
        if (data != null) {
            val appWidgetId = data?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1)
            val customWidgetManager = data.getStringExtra("")

        }
    }

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val inputMethodManager: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            if (inputMethodManager != null) {
                inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }
    }

}