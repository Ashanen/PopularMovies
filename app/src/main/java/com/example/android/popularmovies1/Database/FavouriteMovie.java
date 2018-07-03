package com.example.android.popularmovies1.Database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favourite_movies_table")
public class FavouriteMovie {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String originalTitle;

    private String releaseDate;
    private String posterPath;
    private String voteAverage;
    private String overview;
    private String backdropPatch;



    public FavouriteMovie(String originalTitle, String releaseDate, String posterPath, String voteAverage, String overview, String backdropPatch) {
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.backdropPatch = backdropPatch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPatch() {
        return backdropPatch;
    }

    public void setBackdropPatch(String backdropPatch) {
        this.backdropPatch = backdropPatch;
    }

}
