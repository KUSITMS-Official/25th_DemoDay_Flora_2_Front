package com.example.flora;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL = "http://3.35.252.206:8080/"; // 서버 URL
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static RetrofitApi getAPIService() {
        return getInstance().create(RetrofitApi.class);
    }

    public static Retrofit getInstance() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();

        return retrofit;

    }
}
