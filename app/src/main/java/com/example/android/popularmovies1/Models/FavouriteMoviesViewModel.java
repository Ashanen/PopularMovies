package com.example.android.popularmovies1.Models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.popularmovies1.Database.FavouriteMovie;
import com.example.android.popularmovies1.Database.FavouriteMoviesRepository;

import java.util.List;

/**
 * The ViewModel's role is to provide data to the UI and survive configuration changes.
 * A ViewModel acts as a communication center between the Repository and the UI.
 * You can also use a ViewModel to share data between fragments.
* */

public class FavouriteMoviesViewModel extends AndroidViewModel {

    private FavouriteMoviesRepository moviesRepository;
    private LiveData<List<FavouriteMovie>> mAllFavourites;

    public FavouriteMoviesViewModel (Application application){
        super(application);
        moviesRepository = new FavouriteMoviesRepository(application);
        mAllFavourites = moviesRepository.getAllFavourites();
    }

    public void getMovieById(int id) {
         moviesRepository.getMovieById(id);
    }

    public void deleteMovieById(int id) {
        moviesRepository.deleteMovieById(id);
    }
    public LiveData<FavouriteMovie> getMovieByTitle(String title) {
        return moviesRepository.getMovieByTitle(title);
    }

    public void deleteMovieByTitle(String title) {
        moviesRepository.deleteMovieByTitle(title);
    }

    public LiveData<List<FavouriteMovie>> getmAllFavourites(){ return mAllFavourites;}

    public void insert (FavouriteMovie favouriteMovie){moviesRepository.insert(favouriteMovie);}
}
