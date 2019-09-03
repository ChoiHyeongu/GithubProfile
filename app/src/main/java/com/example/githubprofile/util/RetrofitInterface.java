package com.example.githubprofile.util;

import com.example.githubprofile.model.GithubProfile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("/ChoiHyeongu")
    Call<GithubProfile> getUser();
}
