package com.example.githubprofile.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {

    private static RetrofitConnection ourInstance = new RetrofitConnection();

    public static RetrofitConnection getInstance() {
        return ourInstance;
    }

    private RetrofitConnection() {
    }

    String githubURL= "https://api.github.com/users/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(githubURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitInterface server = retrofit.create(RetrofitInterface.class);

    public RetrofitInterface getServer() {
        return server;
    }
}
