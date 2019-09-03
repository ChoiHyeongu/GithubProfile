package com.example.githubprofile.presenter;

import android.util.Log;

import com.example.githubapiwithretrofit.model.GithubProfile;
import com.example.githubapiwithretrofit.util.RetrofitConnection;

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
        Call<GithubProfile> call =  retrofitConnection.getServer().getUser();

        call.enqueue(new Callback<GithubProfile>() {
            @Override
            public void onResponse(Call<GithubProfile> call, Response<GithubProfile> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onSuccessful");
                } else {
                    Log.d(TAG, "onFailure");
                }
            }

            @Override
            public void onFailure(Call<GithubProfile> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.toString());
            }
        });
    }
}
