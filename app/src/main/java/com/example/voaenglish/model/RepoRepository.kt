package com.example.voaenglish.model

import android.util.Log
import com.example.voaenglish.model.api.ApiClient
import com.example.voaenglish.model.api.RssApiClient
import com.example.voaenglish.network.GitHubClient
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    //GET repo list
    fun getRepoList(onResult: (isSuccess: Boolean, response: GitResponse?) -> Unit) {
        ApiClient.instance.getRepo().enqueue(object : Callback<GitResponse> {
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body()!!)
                    call?.let { Log.d("getRepoList", call.request().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    fun getInboxList(onResult: (isSucces: Boolean, response: List<Message>?) -> Unit) {
        GitHubClient.getGitHubService().inbox.enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body()!!)
                    call?.let { Log.d("getInboxList", call?.request().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                onResult(false, null)
            }

        })
    }

    fun getRssFeed(onResult: (isSucces: Boolean, response: RssFeed?) -> Unit) {
        RssApiClient.instance.getRss("https://learningenglish.voanews.com/api/zkm-qem\$-o").enqueue(object : Callback<RssFeed> {
            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body()!!)
                    call?.let { Log.d("getRssFeed", call?.request().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    fun getProjectList(onResult: (isSucces: Boolean, response: List<Project>?) -> Unit) {
        GitHubClient.getGitHubService().getProjectList("Google").enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body()!!)
                    call?.let { Log.d("getProjectList", call?.request().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                onResult(false, null)
            }

        })
    }


    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE
                ?: RepoRepository().also {
                    INSTANCE = it
                }
    }

}