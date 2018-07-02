package com.example.android.popularmovies1.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {FavouriteMovie.class}, version = 2)
public abstract class FavouriteMoviesRoomDatabase extends RoomDatabase {

    public abstract FavouritiesDao favouritiesDao();

    private static FavouriteMoviesRoomDatabase INSTANCE;

    public static FavouriteMoviesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavouriteMoviesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavouriteMoviesRoomDatabase.class, "favourites_database").addCallback(callback).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
    public static class PopulateFavouritesAsync extends AsyncTask<Void,Void,Void> {
        FavouritiesDao favouritiesDao;

        PopulateFavouritesAsync(FavouriteMoviesRoomDatabase favouriteMoviesRoomDatabase){
            favouritiesDao = favouriteMoviesRoomDatabase.favouritiesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            favouritiesDao.deleteAll();
            return null;
        }
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateFavouritesAsync(INSTANCE).execute();
        }
    };
}
