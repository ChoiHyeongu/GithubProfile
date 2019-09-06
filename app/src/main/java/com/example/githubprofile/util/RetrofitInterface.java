package com.example.githubprofile.util;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("ChoiHyeongu")
    Call<JsonObject> getUser();
}
