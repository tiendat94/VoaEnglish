package com.example.voaenglish

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.model.Item
import kotlinx.android.synthetic.main.activity_repo_detail.*

class RepoDetailActivity : BaseActivity() {

    companion object {
        fun gotoActivityRepoDetail(context: Context, itemData: Item?) {
            val intent = Intent(context, RepoDetailActivity::class.java)
            intent.putExtra("html", itemData?.html_url)
            context.startActivity(intent)
        }

    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_repo_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val html_url = intent.extras?.getString("html");

        setupWebView()
        setClickListeners()

        html_url?.let {
            repo_web_view?.loadUrl(html_url)
        }

        Log.d("RepoDetailActivity", "onCreate")
    }

    private fun setClickListeners() {
        repo_back_button.setOnClickListener {
            repo_web_view.goBack()
        }
        repo_forward_button.setOnClickListener {
            repo_web_view.goForward()
        }

        repo_refresh_button.setOnClickListener {
            repo_web_view.reload()
        }
    }

    private fun setupWebView() {
        repo_web_view.setInitialScale(1)
        val webSettings = repo_web_view.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true

        repo_web_view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (repo_back_button != null && repo_forward_button != null && repo_web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = repo_web_view.canGoBack()
                    repo_forward_button.isEnabled = repo_web_view.canGoForward()
                    repo_progress_view.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (repo_back_button != null && repo_forward_button != null && repo_web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = repo_web_view.canGoBack()
                    repo_forward_button.isEnabled = repo_web_view.canGoForward()
                    repo_progress_view.visibility = View.GONE
                }
            }
        }
    }

    /**
     * Activity created
     */

    override fun onStart() {
        super.onStart()
        Log.d("RepoDetailActivity", "onStart");
    }

    override fun onResume() {
        super.onResume()
        Log.d("RepoDetailActivity", "onResume")
    }

    /**
     * Activity rotated
     */

    override fun onPause() {
        super.onPause()
        Log.d("RepoDetailActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("RepoDetailActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RepoDetailActivity", "onDestroy")
    }
}