package com.example.githubprofile.presenter;

import android.util.Log;

import com.example.githubprofile.util.RetrofitConnection;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements Presenter.Present{

    final String TAG = "ProfilePresenter";

    Presenter.View view;

    @Override
    public void attachView(Presenter.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadUser() {

        RetrofitConnection retrofitConnection = RetrofitConnection.getInstance();
        Call<JSONObject> call =  retrofitConnection.getServer().getUser();

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onSuccessful : " + response.body().toString());
                } else {
                    Log.d(TAG, "onFailure");
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.toString());
            }
        });
    }
}
