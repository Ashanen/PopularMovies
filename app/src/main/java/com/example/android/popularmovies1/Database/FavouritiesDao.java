package com.example.android.popularmovies1.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface FavouritiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavouriteMovie favouriteMovie);

    @Query("DELETE from favourite_movies_table")
    void deleteAll();

    @Query("SELECT * FROM favourite_movies_table")
    LiveData<List<FavouriteMovie>> getAllFavourites();

    @Query("SELECT * FROM favourite_movies_table WHERE id = :id")
    FavouriteMovie getMovieById(int id);

    @Query("DELETE FROM favourite_movies_table WHERE id = :id")
    void deleteMovieById(int id);

    @Query("DELETE FROM favourite_movies_table WHERE originalTitle = :originalTitle")
    void deleteMovieByTitle(String originalTitle);
}
