package com.example.android.popularmovies1.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies1.R;

public class FavouritesFragment extends Fragment {
    public static FavouritesFragment newInstance(){return new FavouritesFragment();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_favourites_movies, container, false);
    }


}
