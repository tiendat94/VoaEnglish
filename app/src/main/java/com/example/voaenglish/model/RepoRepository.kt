package com.example.voaenglish.model

import android.util.Log
import android.view.View
import com.example.voaenglish.base.CallbackResApiLogout
import com.example.voaenglish.model.api.ApiClient
import com.example.voaenglish.model.api.RssApiClient
import com.example.voaenglish.network.GitHubClient
import com.google.gson.JsonElement
import me.toptas.rssconverter.RssFeed
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
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
                    response?.let { Log.d("getRepoList", response?.body().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    //get Filter Schedule
    fun getFilterSchedule(onResult: (isSuccess: Boolean, response: FilterResponse?) -> Unit) {
        GitHubClient.getGitHubService().filterSchedule.enqueue(object : Callback<FilterResponse> {
            override fun onResponse(call: Call<FilterResponse>, response: Response<FilterResponse>) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body())
                    call?.let { Log.d("getFilterSchedule", call.request().toString()) }
                    response?.let { Log.d("getFilterSchedule", response?.body().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<FilterResponse>, t: Throwable) {
                onResult(false, null)
            }

        })
    }

    //GET news list
    fun getNewList(onResult: (isSucces: Boolean, response: NewsResponse?) -> Unit) {
        ApiClient.instance.getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body()!!)
                    response?.let { Log.d("getNewList", it?.body().toString()) }
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
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