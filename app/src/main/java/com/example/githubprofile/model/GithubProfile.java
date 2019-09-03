package com.example.githubprofile.model;

public class GithubProfile {

    private String username;
    private String followers;
    private String follwing;
    private String bio;
    private int repo;
    private int maxContribution;
    private int todayContribution;

    public class Builder{

        // Required parameters
        private String username;
        private String followers;
        private String following;
        private int repo;
        private int maxContribution;
        private int todayContribution;

        // Optional parameters
        private String bio = "";

        public Builder(String username, String followers, String following, int repo, int maxContribution, int todayContribution) {
            this.username = username;
            this.followers = followers;
            this.following = following;
            this.repo = repo;
            this.maxContribution = maxContribution;
            this.todayContribution = todayContribution;
        }

        public Builder(String bio) {
            this.bio = bio;
        }
    }

    private GithubProfile(Builder builder) {
        username = builder.username;
        followers = builder.followers;
        follwing = builder.following;
        bio = builder.bio;
        repo = builder.repo;
        maxContribution = builder.maxContribution;
        todayContribution = builder.todayContribution;
    }
}
