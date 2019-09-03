package com.example.githubprofile.presenter;

public interface Presenter {

    interface View{

    }

    interface Present{
        void attachView(View view);
        void detachView();
        void loadUser();
    }
}
