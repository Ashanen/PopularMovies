package com.example.android.popularmovies1.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse {
    @SerializedName("results")
    private final List<Review> results;

    public ReviewsResponse(List<Review> results) {
        this.results = results;
    }

    public List<Review> getListOfReviews() {
        return results;
    }
}
