package com.example.android.popularmovies1.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * A Repository is a class that abstracts access to multiple data sources.
 * The Repository is not part of the Architecture Components libraries,
 * but is a suggested best practice for code separation and architecture.
 * A Repository class handles data operations.
 * It provides a clean API to the rest of the app for app data.
 */

public class FavouriteMoviesRepository {
    private FavouritiesDao mFavouritiesDao;
    private LiveData<List<FavouriteMovie>> mFavouriteListLiveData;

    public FavouriteMoviesRepository(Application application) {
        FavouriteMoviesRoomDatabase db = FavouriteMoviesRoomDatabase.getDatabase(application);
        mFavouritiesDao = db.favouritiesDao();
        mFavouriteListLiveData = mFavouritiesDao.getAllFavourites();
    }

    public LiveData<List<FavouriteMovie>> getAllFavourites() {
        return mFavouriteListLiveData;
    }

    public void insert(FavouriteMovie favouriteMovie) {
        new insertAsyncTask(mFavouritiesDao).execute(favouriteMovie);
    }
    public FavouriteMovie getMovieById(int id) {
        return mFavouritiesDao.getMovieById(id);
    }

    public void deleteMovieById(int id) {
        mFavouritiesDao.deleteMovieById(id);
    }
    public void deleteMovieByTitle(String title) {
        mFavouritiesDao.deleteMovieByTitle(title);
    }

    public static class insertAsyncTask extends AsyncTask<FavouriteMovie, Void, Void> {
        private FavouritiesDao mAsyncTaskDao;

        insertAsyncTask(FavouritiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavouriteMovie... favouriteMovies) {
            mAsyncTaskDao.insert(favouriteMovies[0]);
            return null;
        }
    }
}

