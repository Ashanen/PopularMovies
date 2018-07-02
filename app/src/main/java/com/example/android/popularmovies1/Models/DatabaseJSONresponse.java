package com.example.android.popularmovies1.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;




/**
 * Created by Filip Zych on 10,April,2018
 */

/**
 * POJO class for Retrofit
 */
public class DatabaseJSONresponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movies> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;


    public DatabaseJSONresponse(int page, List<Movies> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public List<Movies> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

}
