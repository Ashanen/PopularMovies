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

    public void deleteMovieByTitle(String title) {
        new deleteAsyncTask(mFavouritiesDao).execute(title);
    }
    public void deleteMovieById(Integer id) {
        new deleteAsyncTaskById(mFavouritiesDao).execute(id);
    }
    public LiveData<FavouriteMovie> getMovieById(int id) {
        new getMovieByIdAsync(mFavouritiesDao).execute(id);
        return null;
    }
    public LiveData<FavouriteMovie> getMovieByTitle(String title) {
//        new getMovieByTitleAsync(mFavouritiesDao).execute(title);
        return mFavouritiesDao.getMovieByTitle(title);
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

    public static class deleteAsyncTask extends AsyncTask<String, Void, Void> {
        private FavouritiesDao mAsyncTaskDao;

        deleteAsyncTask(FavouritiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... title) {
            mAsyncTaskDao.deleteMovieByTitle(title[0]);

            return null;
        }
    }

    public static class deleteAsyncTaskById extends AsyncTask<Integer, Void, Void> {
        private FavouritiesDao mAsyncTaskDao;

        deleteAsyncTaskById(FavouritiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... id) {
            mAsyncTaskDao.deleteMovieById(id[0]);

            return null;
        }
    }

    public static class getMovieByIdAsync extends AsyncTask<Integer, Void, Void> {
        private FavouritiesDao mAsyncTaskDao;

        getMovieByIdAsync(FavouritiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... id) {
            mAsyncTaskDao.getMovieById(id[0]);

            return null;
        }
    }
    public static class getMovieByTitleAsync extends AsyncTask<FavouriteMovie, Void, Void> {
        private FavouritiesDao mAsyncTaskDao;

        getMovieByTitleAsync(FavouritiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavouriteMovie... movie) {
//            mAsyncTaskDao.getMovieByTitle(movie[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

