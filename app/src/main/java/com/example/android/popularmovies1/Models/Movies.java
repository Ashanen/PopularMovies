package com.example.android.popularmovies1.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Filip Zych on 13,April,2018
 */
public class Movies{

    @SerializedName("id")
    private Integer moviesId;

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    public Integer getMoviesId() {
        return moviesId;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Movies(String posterPath, String overview, String releaseDate,
                 String originalTitle, String backdropPath, Double voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.title = originalTitle;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
    }

}
