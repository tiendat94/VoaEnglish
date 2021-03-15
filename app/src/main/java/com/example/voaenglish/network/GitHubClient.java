package com.example.voaenglish.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    public static Retrofit retrofit = null;

    public static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIzZjIxMjRhMC1kNzRkLTQzNWYtM2VkOS0wOGQ4YTY0OTM5ZmYiLCJ1bmlxdWVfbmFtZSI6ImFkbWluIiwibmJmIjoxNjE0NTAxMzg2LCJleHAiOjE2MTQ1MDczODYsImlhdCI6MTYxNDUwMTM4Nn0.cEfd-oLpp1EmhZA96yTkCUMPzPx87nCpa3uUIJ04lqo";

    public static GitHubService getGitHubService() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            builder.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.addHeader("Authorization", token);
                    requestBuilder.removeHeader("Content-type");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(GITHUB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(GitHubService.class);
    }

}
