package com.example.moviewer;

public class Movie {
    int id;
    String title;
    String overview;
    String path;
    String rating;
    String releaseDate;

    public Movie(int id, String title, String overview, String path, String rating, String releaseDate) {
        this.title = title;
        this.overview = overview;
        this.path = path;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public Movie(){}

    public int getId() {
        return id;
    }

    public void setId(int id){ this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
