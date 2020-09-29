package com.example.voaenglish.model.api

import com.example.voaenglish.model.GitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    fun getRepo(@Query("q") search : String = "trending",@Query("sort") sort : String = "stars") : Call<GitResponse>

}