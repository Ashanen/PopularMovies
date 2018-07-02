package com.example.android.popularmovies1.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TrailerResponse {

    @SerializedName("results")
    private final List<Trailer> results;

    public TrailerResponse(List<Trailer> results) {
        this.results = results;
    }

    public List<Trailer> getListOfTrailers() {
        return results;
    }

}

