package com.example.githubprofile.util;

import com.example.githubprofile.model.GithubProfile;

public interface GithubProfileCallback {
    void onSuccess(GithubProfile githubProfile);
    void onError();
}
