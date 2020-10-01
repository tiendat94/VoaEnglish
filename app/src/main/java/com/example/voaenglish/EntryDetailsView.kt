package com.example.voaenglish

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.FileProvider
import com.example.voaenglish.utils.Constants
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.io.IOException
import java.lang.StringBuilder

class EntryDetailsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int? = 0) : WebView(context, attrs, defStyleAttr!!) {

    private val TEXT_HTLM = "text/html"
    private val HTML_IMG_REGEX = "(?i)<[/]?[ ]?img(.|\n)*?>"

    companion object {
        val CSS = "<head><style type='text/css'> " +
                "body {max-width: 100%; margin: 0.3cm; font-family: sans-serif-light; color: #C0C0C0" + "; background-color:#202020" + "; line-height: 150%} " +
                "* {max-width: 100%; word-break: break-word}" +
                "h1, h2 {font-weight: normal; line-height: 130%} " +
                "h1 {font-size: 170%; margin-bottom: 0.1em} " +
                "h2 {font-size: 140%} " +
                "a {color: #0099CC}" +
                "h1 a {color: inherit; text-decoration: none}" +
                "img {height: auto} " +
                "pre {white-space: pre-wrap; direction: ltr;} " +
                "blockquote {border-left: thick solid #686B6F" + "; background-color: #383B3F" + "; margin: 0.5em 0 0.5em 0em; padding: 0.5em} " +
                "p {margin: 0.8em 0 0.8em 0} " +
                "p.subtitle {color:  #8C8C8C" + "; border-top:1px " + "; border-bottom:1px " + "; padding-top:2px; padding-bottom:2px; font-weight:800 } " +
                "ul, ol {margin: 0 0 0.8em 0.6em; padding: 0 0 0 1em} " +
                "ul li, ol li {margin: 0 0 0.8em 0; padding: 0} " +
                "</style><meta name='viewport' content='width=device-width'/></head>"
    }


    private val BODY_START = "<body dir =\"auto\">"
    private val BODY_END = "</body>"
    private val TITLE_START = "<h1><a href='"
    private val TITLE_MIDDLE = "'>"
    private val TITLE_END = "</a></h1>"
    private val SUBTITLE_START = "<p class='subtitle'>"
    private val SUBTITLE_END = "</p>"

    init {
        // For scrolling
        isHorizontalScrollBarEnabled = false
        settings?.useWideViewPort = false
        settings?.allowFileAccess = true
        @SuppressLint("SetJavaScriptEnabled")
        settings?.javaScriptEnabled = true
        setBackgroundColor(Color.parseColor("#202020"))

        //Text zoom level from preferences
        val fontSize = 16.toInt()
        if (fontSize != null) {
            settings?.textZoom = 100 + fontSize * 20
        }


        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                try {
                    if (url!!.startsWith(Constants.FILE_SCHEME)) {
                        val file = File(url!!.replace(Constants.FILE_SCHEME, ""))
                        val contentUri = FileProvider.getUriForFile(context, "com.example.voaenglish.fileprovider", file)
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setDataAndType(contentUri, "image/jpeg")
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                return true
            }
        }
    }

    fun setEntry(description: String?, link: String?, title: String?, preferFullText: Boolean) {
        if (description == null) {
            loadDataWithBaseURL("", "", TEXT_HTLM, Constants.UTF8, null)
        } else {
            doAsync {
                uiThread {
                    if (true) {
                        if (settings?.blockNetworkImage) {
                            loadData("", TEXT_HTLM, Constants.UTF8)
                            settings?.blockNetworkImage = false
                        }
                    } else {
                        settings?.blockNetworkImage = true
                    }
                }
                val html = StringBuilder(CSS)
                        .append(BODY_START)
                        .append(TITLE_START).append(link).append(TITLE_MIDDLE).append(title).append(TITLE_END)
                        .append(description)
                        .append(BODY_END)
                        .toString()
                loadDataWithBaseURL("", CSS, TEXT_HTLM, Constants.UTF8, null)

            }

        }
    }

}