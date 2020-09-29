package com.example.voaenglish.model;

public class GitHubRepo {
    public final int id;
    public final String name;
    public final String htmUrl;
    public final String description;
    public final String language;
    public final int stargazersCount;

    public GitHubRepo(int id, String name, String htmUrl, String description, String language, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmUrl = htmUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHtmUrl() {
        return htmUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }
}
