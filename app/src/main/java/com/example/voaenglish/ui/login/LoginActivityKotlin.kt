package com.example.voaenglish.ui.login

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.voaenglish.R
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.base.CallbackResApiLogout
import com.example.voaenglish.model.Message
import com.example.voaenglish.model.RepoRepository
import com.example.voaenglish.network.GitHubClient
import com.example.voaenglish.viewmodel.RepoListViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityKotlin : BaseActivity() {

    private lateinit var mRewardedAd: RewardedAd

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var repoListViewModel: RepoListViewModel

    class Config(var buildTpe: String, var version: String)

    val map = hashMapOf<String, Config>()

    fun configurationFor(id: String) = map[id]?.let { config ->
        config.apply {
            buildTpe = "DEBUG"
            version = "1.2"
        }
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    fun getInboxNew(onResult: (isSuccess: Boolean, response: JsonElement?) -> Unit) {
        GitHubClient.getGitHubService().inboxNew.enqueue(object : CallbackResApiLogout(this) {
            override fun onFailure(code: Int) {

            }

            override fun onReceivedResponse(jsonElement: JsonElement) {
                if (jsonElement != null) {
                    onResult(true, jsonElement)
                    Toast.makeText(applicationContext, "ping success", Toast.LENGTH_LONG).show()
                }
            }

        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        test();

        getInboxNew { isSuccess, response ->
            if (isSuccess) {

            }
        }
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        getUserTask().execute()

        val result = Processor().performEvent(false, object : Processor.ActionCallback {
            override fun success(): String? {
                return "Success"
            }

            override fun failure(): String? {
                return "Failure"
            }
        })



        result?.let { Log.d("LoginActivityKotlin", result) }

        repoListViewModel = ViewModelProviders.of(this@LoginActivityKotlin).get(RepoListViewModel::class.java)
        repoListViewModel?.fetchRepoList()
        repoListViewModel?.repoLiveLive?.observe(this@LoginActivityKotlin, Observer {
            it?.let {
                //Log.d("LoginActivityKotlin", it.toString())
                Toast.makeText(this@LoginActivityKotlin, it.get(1).description, Toast.LENGTH_LONG).show()
            }
        })

        RepoRepository.getInstance().getFilterSchedule { isSuccess, response ->
            if (isSuccess) {

            }
        }

        repoListViewModel?.fetchNewsList()
        repoListViewModel?.repoListNewsLive?.observe(this@LoginActivityKotlin, Observer {
            it?.let {
                Log.d("LoginActivityKotlin", it.toString())
            }
        })

        loadRewardedAd()

    }

    private fun loadRewardedAd() {
        if (!(::mRewardedAd.isInitialized) || !mRewardedAd.isLoaded) {
            mRewardedAd = RewardedAd(this, R.string.rewarded_video.toString())
            mRewardedAd.loadAd(
                    AdRequest.Builder().build(), object : RewardedAdCallback() {
                override fun onUserEarnedReward(p0: RewardItem) {

                }
            }
            )
        }
    }

    fun test() {
        GitHubClient.getGitHubService().inbox.enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    Log.d("LoginActivityKotlin", response.body()!![0].message)

                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {

            }

        })
    }

    //asysntask get userName
    class getUserTask() : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String {
            return "Hello AsyncTask"
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            result?.let { Log.d("LoginActivityKotlin", it) };
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}

private fun RewardedAd.loadAd(build: AdRequest?, rewardedAdCallback: RewardedAdCallback) {

}


