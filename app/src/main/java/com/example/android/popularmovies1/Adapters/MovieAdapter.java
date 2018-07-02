package com.example.android.popularmovies1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies1.DetailsActivity;
import com.example.android.popularmovies1.Models.Movies;
import com.example.android.popularmovies1.R;
import com.example.android.popularmovies1.Utils.ConstantValues;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Filip Zych on 10,May,2018
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movies> mMovieList;
    private Context mContext;

    public MovieAdapter(List<Movies> mMovieList, Context context) {
        this.mMovieList = mMovieList;
        this.mContext = context;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView moviePoster;

        private MovieViewHolder(View view) {
            super(view);
            moviePoster = view.findViewById(R.id.movie_poster);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        Bundle extras = new Bundle();

                        extras.putString(ConstantValues.ORIGINAL_TITLE,
                                mMovieList.get(position).getTitle());
                        extras.putString(ConstantValues.POSTER_PATH,
                                mMovieList.get(position).getPosterPath());
                        extras.putString(ConstantValues.BACKDROP_PATH,
                                mMovieList.get(position).getBackdropPath());
                        extras.putString(ConstantValues.OVERVIEW,
                                mMovieList.get(position).getOverview());
                        extras.putString(ConstantValues.VOTE_AVERAGE,
                                Double.toString(mMovieList.get(position).getVoteAverage()));
                        extras.putString(ConstantValues.RELEASE_DATE,
                                mMovieList.get(position).getReleaseDate());
                        extras.putInt(ConstantValues.MOVIES_KEY, mMovieList.get(position).getMoviesId());

                        intent.putExtras(extras);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        mContext.startActivity(intent, extras);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_items, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        String poster = ConstantValues.MOVIE_POSTER_PATH + mMovieList.get(position).getPosterPath();

        Picasso.get()
                .load(poster)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }
}
