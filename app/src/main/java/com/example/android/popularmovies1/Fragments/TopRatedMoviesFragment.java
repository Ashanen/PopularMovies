package com.example.android.popularmovies1.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies1.R;

/**
 * Created by Filip Zych on 17,June,2018
 */
public class TopRatedMoviesFragment extends Fragment {
    public static android.support.v4.app.Fragment newInstance() {
        return new TopRatedMoviesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false);
    }
}
