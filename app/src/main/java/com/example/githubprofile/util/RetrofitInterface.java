package com.example.githubprofile.util;

import com.example.githubprofile.model.GithubProfile;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("ChoiHyeongu")
    Call<JSONObject> getUser();
}
