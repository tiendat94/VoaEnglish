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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RepoDetailActivity : BaseActivity() {

    private val html = "<head><style type='text/css'> body {max-width: 100%; margin: 0.3cm; font-family: sans-serif-light; color: #C0C0C0; background-color:#202020; line-height: 150%} * {max-width: 100%; word-break: break-word}h1, h2 {font-weight: normal; line-height: 130%} h1 {font-size: 170%; margin-bottom: 0.1em} h2 {font-size: 140%} a {color: #0099CC}h1 a {color: inherit; text-decoration: none}img {height: auto} pre {white-space: pre-wrap; direction: ltr;} blockquote {border-left: thick solid #686B6F; background-color:#383B3F; margin: 0.5em 0 0.5em 0em; padding: 0.5em} p {margin: 0.8em 0 0.8em 0} p.subtitle {color: #8C8C8C; border-top:1px solid #303030; border-bottom:1px solid #303030; padding-top:2px; padding-bottom:2px; font-weight:800 } ul, ol {margin: 0 0 0.8em 0.6em; padding: 0 0 0 1em} ul li, ol li {margin: 0 0 0.8em 0; padding: 0} </style><meta name='viewport' content='width=device-width'/></head><body dir=\"auto\"><h1><a href='https://www.businessinsider.com/business-insiders-top-advertising-and-media-stories-for-september-29-2020-9'>Complex Networks has a new youth culture agency</a></h1><p class='subtitle'>05:15 &mdash; Tanya Dua</p>Summary List Placement\n" +
            "<p>Hi! Welcome to the Insider Advertising daily for September 29. I'm Tanya Dua, a senior advertising reporter at Business Insider, filling in for my colleague Lauren Johnson while she's away this week. <a href=\"http://newsletter.businessinsider.com/join/4np/insider-advertising\">Subscribe here to get this newsletter in your inbox every weekday.</a> Send me feedback or tips at <a>tdua@businessinsider.com.<br><br><strong>Today's news:</strong> Complex Networks branches out into product development, </a>how to crack Instagram's competitive interview process, and the up-and-coming marketing professionals shaking things up at brands.</p>  \n" +
            "<p><a href=\"https://www.businessinsider.com/inside-complex-networks-plan-to-develop-products-for-other-brands-2020-9\"><img src=\"https://static5.businessinsider.com/image/5e879467c0232058574fbb97-2050/screen shot 2020-04-03 at 35315 pm.png\" alt=\"Zac Efron Hot Ones\"></a></p> \n" +
            "<h3><a href=\"https://www.businessinsider.com/inside-complex-networks-plan-to-develop-products-for-other-brands-2020-9\">Complex Networks made millions selling products like hot sauce, and now it's applying that playbook to other brands</a></h3> \n" +
            "<ul> \n" +
            " <li>Complex Networks has parlayed its sneaker- and hip-hop-focused media brands into a business selling related products like hot sauce, skateboards, and hoodies.</li> \n" +
            " <li>Now it wants to apply that know-how to help brands like Pepsi and Champs develop products of their own with a new agency, Climate, reports Lucia Moses.</li> \n" +
            " <li>It has plenty of competition, but Complex thinks its leadership, audience data, and media distribution will give it an edge.</li> \n" +
            "</ul> \n" +
            "<h3><a href=\"https://www.businessinsider.com/inside-complex-networks-plan-to-develop-products-for-other-brands-2020-9\">Click here to read the full story.&nbsp;</a></h3>  \n" +
            "<p><a href=\"https://www.businessinsider.com/how-to-get-a-job-at-instagram-2020-9\"><img src=\"file:///data/user/0/net.frju.flym/cache/images/0ba7e7e6cc02e63b287b937399a0ed02d6798cdf__ee80be72dbb491b5dbd3d8226b2e137cc8f18346\" alt=\"adam mosseri instagram\"></a></p> \n" +
            "<h3><a href=\"https://www.businessinsider.com/how-to-get-a-job-at-instagram-2020-9\">Insiders reveal how to break through Instagram's competitive interview process and land a job there</a></h3> \n" +
            "<ul> \n" +
            " <li>Instagram, which has exploded since Facebook acquired it for \$1 billion in 2012, has a highly competitive hiring process, report Lauren Johnson and Sydney Bradley.</li> \n" +
            " <li>Former employees and experts they spoke to shared tips and tricks to nail the interview process, saying that referrals play a big role in giving candidates a leg up, as does knowing Instagram and its competitors' features.&nbsp;</li> \n" +
            " <li>Candidates go through a series of interviews at Instagram and meet with multiple people, and should be open to roles other than the ones they applied for as Instagram even tweaks roles for specific candidates.</li> \n" +
            "</ul> \n" +
            "<h3><a href=\"https://www.businessinsider.com/how-to-get-a-job-at-instagram-2020-9\">Read the full story here.</a></h3>  \n" +
            "<h3><a href=\"https://www.businessinsider.com/the-25-rising-stars-of-brand-marketing-pg-google-facebook-2020-9\"><img src=\"https://static5.businessinsider.com/image/5f72431074fe5b0018a8dd6a-1200/rising stars in brand marketing 4x3.png\" alt=\"rising stars in brand marketing 4x3\"></a></h3> \n" +
            "<h3><a href=\"https://www.businessinsider.com/the-25-rising-stars-of-brand-marketing-pg-google-facebook-2020-9\">Meet the rising stars of brand marketing</a></h3> \n" +
            "<ul> \n" +
            " <li>Business Insider has launched its first list recognizing the rising marketing talent at brands.</li> \n" +
            " <li>I profiled 25 such up-and-comers who are shepherding breakthrough campaigns for their companies, setting up internal agencies, or using media to reach consumers in new ways.</li> \n" +
            " <li>These marketing professionals come from established brands like Procter &amp; Gamble and Frito-Lay as well as challenger ones like Kin Euphorics and Verb Energy.</li> \n" +
            "</ul> \n" +
            "<h3><a href=\"https://www.businessinsider.com/the-25-rising-stars-of-brand-marketing-pg-google-facebook-2020-9\">Click here to read the full story.</a></h3>  \n" +
            "<h3><strong>Other stories we're reading:</strong></h3> \n" +
            "<ul> \n" +
            " <li><a href=\"https://www.businessinsider.com/pr-giant-edelman-has-ended-an-exclusive-partnership-with-cision-2020-9\">Edelman's partnership with Cision was once positioned as the 'first of its kind,' but after its first year, the deal is no longer exclusive</a> (Business Insider)</li> \n" +
            " <li><a href=\"https://www.businessinsider.com/brands-are-hiring-pro-animal-crossing-players-advertise-in-game-2020-9\">Brands are hiring Animal Crossing players to help them sneak into the digital world, launch products, and create custom in-game experiences</a> (Business Insider)</li> \n" +
            " <li><a href=\"https://www.businessinsider.com/tiktok-court-ban-cripple-user-numbers-2020-9\">Here's the data showing why TikTok's court victory was vital</a> (Business Insider)</li> \n" +
            " <li><a href=\"https://www.cnbc.com/2020/09/28/google-to-enforce-30percent-cut-on-in-app-purchases-next-year.html\">Google to enforce 30% cut on in-app purchases next year</a> (CNBC)</li> \n" +
            " <li><a href=\"https://www.wsj.com/articles/disney-sells-ad-tech-firm-truex-to-gimbal-11601317342\">Disney Sells Ad-Tech Firm TrueX to Gimbal</a> (The Wall Street Journal)</li> \n" +
            "</ul> \n" +
            "<p>Thanks for reading! Feel free to reach me at <a>tdua@businessinsider.com</a>&nbsp;and&nbsp;<a href=\"http://newsletter.businessinsider.com/join/4np/advertising-insider\">subscribe to this daily email here.</a></p> \n" +
            "<p>— Tanya&nbsp;</p>\n" +
            "<p><a href=\"https://www.businessinsider.com/business-insiders-top-advertising-and-media-stories-for-september-29-2020-9#comments\">Join the conversation about this story »</a></p> \n" +
            "<p>NOW WATCH: <a href=\"https://www.businessinsider.com/what-its-like-make-beer-with-picobrew-c-home-brew-2020-3\">We tested a machine that brews beer at the push of a button</a></p></body>"

    companion object {
        fun gotoActivityRepoDetail(context: Context, description: String?, link: String?, title: String?) {
            val intent = Intent(context, RepoDetailActivity::class.java)
            intent.putExtra("description", description)
            intent.putExtra("link", link)
            intent.putExtra("title", title)
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

        var description = intent?.extras?.getString("description")
        var link = intent?.extras?.getString("link")
        var title = intent?.extras?.getString("title")

       
        setupWebView()
        setClickListeners()

        html?.let {
            repo_web_view.loadDataWithBaseURL("", EntryDetailsView.CSS, "text/html", "UTF-8", null)
        }
        doAsync {
            uiThread {
                entry_view?.setEntry(description, link, title, true)
            }
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
        entry_view.destroy()
    }
}