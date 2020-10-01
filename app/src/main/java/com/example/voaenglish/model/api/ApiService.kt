package com.example.voaenglish.model.api

import com.example.voaenglish.model.GitResponse
import com.example.voaenglish.model.ImageUploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/repositories")
    fun getRepo(@Query("q") search: String = "trending", @Query("sort") sort: String = "stars"): Call<GitResponse>

    @Multipart
    fun uploadImage(@Url url: String?, @Part files: ArrayList<MultipartBody.Part>): Call<ImageUploadResponse>

    @Multipart
    @POST
    fun uploadImageWithBody(@Url url: String?, @QueryMap params: HashMap<String, String>, @Part files: ArrayList<MultipartBody.Part>)
}