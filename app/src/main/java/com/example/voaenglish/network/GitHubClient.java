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
//                    requestBuilder.addHeader("Authorization", MyApplication.getInstance().getDataStoreApp().getAccessToken());
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
