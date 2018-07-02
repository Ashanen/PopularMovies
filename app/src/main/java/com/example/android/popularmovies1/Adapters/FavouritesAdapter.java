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

import com.example.android.popularmovies1.Database.FavouriteMovie;
import com.example.android.popularmovies1.DetailsActivity;
import com.example.android.popularmovies1.R;
import com.example.android.popularmovies1.Utils.ConstantValues;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Filip Zych on 10,May,2018
 */
public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MovieViewHolder> {

    private Context mContext;
    private List<FavouriteMovie> mFavouriteMoviesList;


    public FavouritesAdapter(Context mContext, List<FavouriteMovie> mFavouriteMoviesList) {
        this.mContext = mContext;
        this.mFavouriteMoviesList = mFavouriteMoviesList;
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
                                mFavouriteMoviesList.get(position).getOriginalTitle());
                        extras.putString(ConstantValues.POSTER_PATH,
                                mFavouriteMoviesList.get(position).getPosterPath());
                        extras.putString(ConstantValues.BACKDROP_PATH,
                                mFavouriteMoviesList.get(position).getBackdropPatch());
                        extras.putString(ConstantValues.OVERVIEW,
                                mFavouriteMoviesList.get(position).getOverview());
                        extras.putString(ConstantValues.VOTE_AVERAGE,
                                (mFavouriteMoviesList.get(position).getVoteAverage()));
                        extras.putString(ConstantValues.RELEASE_DATE,
                                mFavouriteMoviesList.get(position).getReleaseDate());

                        intent.putExtras(extras);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        mContext.startActivity(intent,extras);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public FavouritesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_items, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.MovieViewHolder holder, int position) {
        String poster = ConstantValues.MOVIE_POSTER_PATH + mFavouriteMoviesList.get(position).getPosterPath();

        Picasso.get()
                .load(poster)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return (mFavouriteMoviesList == null) ? 0 : mFavouriteMoviesList.size();
    }
}
