package com.example.android.popularmovies1.Models;



import com.google.gson.annotations.SerializedName;

public class Trailer {
    @SerializedName("key")
    private final String key;
    @SerializedName("name")
    private final String name;

    public Trailer(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }


}
