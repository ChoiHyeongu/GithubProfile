package com.example.githubprofile.model;

public class GithubProfile {

    private final String username;
    private final String followers;
    private final String bio;
    private final String following;
    private final String repo;
    private final String maxContribution;
    private final String todayContribution;

    public String getUsername() {
        return username;
    }

    public String getFollowers() {
        return followers;
    }

    public String getBio() {
        return bio;
    }

    public String getFollowing() {
        return following;
    }

    public String getRepo() {
        return repo;
    }

    public String getMaxContribution() {
        return maxContribution;
    }

    public String getTodayContribution() {
        return todayContribution;
    }

    public static class Builder{

        // Required parameters
        private String username;
        private String followers;
        private String following;
        private String repo;
        private String maxContribution;
        private String todayContribution;

        // Optional parameters
        private String bio = "";

        public Builder(String username, String followers, String following, String repo, String maxContribution, String todayContribution) {
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
