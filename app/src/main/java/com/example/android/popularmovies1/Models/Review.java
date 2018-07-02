package com.example.android.popularmovies1.Models;

import com.google.gson.annotations.SerializedName;

public class Review  {
    @SerializedName("author")
    private final String author;
    @SerializedName("content")
    private final String content;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
    }


}
