package com.example.githubprofile.presenter;

import android.util.Log;

import com.example.githubprofile.model.GithubProfile;
import com.example.githubprofile.util.RetrofitConnection;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements Presenter.Present{

    final String TAG = "ProfilePresenter";
    String userJson;

    Presenter.View view;

    public ProfilePresenter(Presenter.View view) {
        this.view = view;
    }

    @Override
    public void attachView(Presenter.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getUserInfo() {

        RetrofitConnection retrofitConnection = RetrofitConnection.getInstance();
        Call<JsonObject> call =  retrofitConnection.getServer().getUser();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    userJson = response.body().toString();
                    Log.d(TAG, "onSuccessful : " + userJson);
                } else {
                    Log.d(TAG, "onFailure");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.toString());
            }
        });
    }

    @Override
    public GithubProfile makeProfileInstance(){

        int followers=0;
        int following=0;
        int repo=0;
        String username="";
        String bio="";

        try {
            JSONObject jsonObject = new JSONObject(userJson);
            followers = jsonObject.getInt("followers");
            following = jsonObject.getInt("following");
            repo = jsonObject.getInt("public_repos");
            username = jsonObject.getString("name");
            bio = jsonObject.getString("bio");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        GithubProfile githubProfile = new GithubProfile
                .Builder(username, followers, following, repo, 0, 0)
                .bio(bio)
                .build();

        return githubProfile;
    }
}
