package com.example.githubprofile.util;

import com.example.githubprofile.model.GithubProfile;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("ChoiHyeongu")
    Call<JsonObject> getUser();
}
