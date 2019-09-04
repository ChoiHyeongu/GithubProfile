package com.example.githubprofile.model;

public class GithubProfile {

    private final String username;
    private final int followers;
    private final int following;
    private final String bio;
    private final int repo;
    private final int maxContribution;
    private final int todayContribution;

    public String getUsername() {
        return username;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public int getRepo() {
        return repo;
    }

    public int getMaxContribution() {
        return maxContribution;
    }

    public int getTodayContribution() {
        return todayContribution;
    }

    public static class Builder{

        // Required parameters
        private String username;
        private int followers;
        private int following;
        private int repo;
        private int maxContribution;
        private int todayContribution;

        // Optional parameters
        private String bio = "";

        public Builder(String username, int followers, int following, int repo, int maxContribution, int todayContribution) {
            this.username = username;
            this.followers = followers;
            this.following = following;
            this.repo = repo;
            this.maxContribution = maxContribution;
            this.todayContribution = todayContribution;
        }

        public Builder bio(String val){
            bio = val;
            return this;
        }

        public GithubProfile build() {
            return new GithubProfile(this);
        }
    }

    private GithubProfile(Builder builder) {
        username = builder.username;
        followers = builder.followers;
        following = builder.following;
        bio = builder.bio;
        repo = builder.repo;
        maxContribution = builder.maxContribution;
        todayContribution = builder.todayContribution;
    }
}
