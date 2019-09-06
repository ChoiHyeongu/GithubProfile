package com.example.githubprofile.presenter;

import com.example.githubprofile.model.GithubProfile;
import com.example.githubprofile.util.GithubProfileCallback;

public interface Presenter {

    interface View{
        void setProfile(GithubProfile githubProfile);
    }

    interface Present{
        void attachView(View view);
        void detachView();
        void getUserInfo(final GithubProfileCallback callback);
    }
}
