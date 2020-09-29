package com.example.voaenglish.model.api

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.example.voaenglish.utils.Constants
import com.google.gson.GsonBuilder
import me.toptas.rssconverter.RssConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RssApiClient {
    val instance: RssService = Retrofit.Builder().run {
        val gson = GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create()
        baseUrl(Constants.BASE_URL)
        addConverterFactory(RssConverterFactory.create())
        build()
    }.create(RssService::class.java)

    private fun createRequestInterceptorClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return if (Constants.DEBUG) {
            OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(Constants.REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                    .readTimeout(Constants.REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(Constants.REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                    .build()
        } else {
            OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(Constants.REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                    .readTimeout(Constants.REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(Constants.REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                    .build()
        }
    }
}