package com.example.githubprofile.presenter;

import com.example.githubprofile.model.GithubProfile;

import java.util.concurrent.BlockingQueue;

public interface Presenter {

    interface View{
        void setProfile(GithubProfile githubProfile);
    }

    interface Present{
        void attachView(View view);
        void detachView();
      GithubProfile makeProfileInstance();
      void getUserInfo();
    }
}
