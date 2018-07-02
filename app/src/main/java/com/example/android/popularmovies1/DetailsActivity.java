package com.example.android.popularmovies1;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies1.APIcontroler.APIclient;
import com.example.android.popularmovies1.APIcontroler.IAPIforRetrofit;
import com.example.android.popularmovies1.Adapters.ReviewsAdapter;
import com.example.android.popularmovies1.Adapters.TrailersAdapter;
import com.example.android.popularmovies1.Database.FavouriteMovie;
import com.example.android.popularmovies1.Database.FavouriteMoviesRepository;
import com.example.android.popularmovies1.Models.FavouriteMoviesViewModel;
import com.example.android.popularmovies1.Models.Review;
import com.example.android.popularmovies1.Models.ReviewsResponse;
import com.example.android.popularmovies1.Models.Trailer;
import com.example.android.popularmovies1.Models.TrailerResponse;
import com.example.android.popularmovies1.Utils.ConstantValues;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Filip Zych on 10,May,2018
 */
public class DetailsActivity extends AppCompatActivity {
    ImageView moviePoster;
    ImageView movieBackdropPath;
    TextView movieName;
    TextView moviePlot;
    TextView movieReleaseDate;
    TextView movieVoteAverage;
    private Toolbar toolbar;
    public RecyclerView trailerRecyclerView;
    public RecyclerView reviewRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private TrailersAdapter trailersAdapter;
    private int movie_id_fromIntent;
    CheckBox checkboxFavourites;

    private FavouriteMoviesRepository repository;
    private FavouriteMovie favouriteMovie;



    private int movieId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        init();

        getDataFromIntent();
        initMovieDetailReviewsView();
    }

    private void init() {
        moviePoster = findViewById(R.id.movie_poster);
        movieBackdropPath = findViewById(R.id.movie_backdrop_path);
        movieName = findViewById(R.id.movie_original_title);
        moviePlot = findViewById(R.id.movie_overview);
        movieReleaseDate = findViewById(R.id.movie_release_date);
        movieVoteAverage = findViewById(R.id.movie_vote_average);
        movieVoteAverage = findViewById(R.id.movie_vote_average);
        checkboxFavourites = findViewById(R.id.fav_checkbox);
    }
    private void initMovieDetailTrailersView() {
        List<Trailer> trailerList = new ArrayList<>();
        TrailersAdapter trailersAdapter = new TrailersAdapter(this, trailerList);
        trailerRecyclerView = findViewById(R.id.trailerRecyclerView);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        trailerRecyclerView.setLayoutManager(layoutManager);

        trailerRecyclerView.setAdapter(trailersAdapter);
        trailersAdapter.notifyDataSetChanged();

        getTrailers();
    }

    private void initMovieDetailReviewsView() {
        List<Review> reviewList = new ArrayList<>();
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(reviewList);
        reviewRecyclerView = findViewById(R.id.reviewRV);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        reviewRecyclerView.setAdapter(reviewsAdapter);

        reviewsAdapter.notifyDataSetChanged();
        getReviews();
        initMovieDetailTrailersView();
    }


    private void getDataFromIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (intent.hasExtra(ConstantValues.ORIGINAL_TITLE)) {
            final String movie_name = bundle.getString(ConstantValues.ORIGINAL_TITLE);
            final String movie_synopsis = bundle.getString(ConstantValues.OVERVIEW);
            final String movie_rating = bundle.getString(ConstantValues.VOTE_AVERAGE);
            final String movie_release_date = bundle.getString(ConstantValues.RELEASE_DATE);
            final String movie_poster = bundle.getString(ConstantValues.POSTER_PATH);
            final String movie_backdrop_path = bundle.getString(ConstantValues.BACKDROP_PATH);

            repository = new FavouriteMoviesRepository(getApplication());

            movie_id_fromIntent = bundle.getInt(ConstantValues.MOVIES_KEY);


            //querying on main thread OMG
            final FavouriteMovie favmovieForCheckBox = repository.getMovieById(movie_id_fromIntent);

            if(favmovieForCheckBox!=null) {
                movieId = favmovieForCheckBox.getId();
                if ( movie_id_fromIntent == movieId) {
                    checkboxFavourites.setChecked(true);
                }
            } else if (movie_id_fromIntent == 0) {
                    checkboxFavourites.setChecked(true);
                }


            String poster = ConstantValues.MOVIE_POSTER_PATH;
            String backdrop = ConstantValues.MOVIE_BACKDROP_IMAGE_PATH;

            Picasso.get()
                    .load(poster + movie_poster)
                    .into(moviePoster);

            Picasso.get()
                    .load(backdrop + movie_backdrop_path)
                    .into(movieBackdropPath);

            movieName.setText(movie_name);
            moviePlot.setText(movie_synopsis);
            movieVoteAverage.setText(movie_rating);
            movieReleaseDate.setText(movie_release_date);

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(movie_name);

            checkboxFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(movie_id_fromIntent != 0) {
                        favouriteMovie = new FavouriteMovie(movie_name, movie_release_date, movie_poster, movie_rating, movie_synopsis, movie_backdrop_path, movie_id_fromIntent, true);
                        if(movie_id_fromIntent == movieId){
                            repository.deleteMovieById(movieId);
                        }else {
                            repository.insert(favouriteMovie);
                        }
                    }else{
                        repository.deleteMovieByTitle(movie_name);
                    }
                }
            });
        }
    }

    private void getTrailers() {
        try {
            IAPIforRetrofit moviesApiInterface =
                    APIclient.getApiClient().create(IAPIforRetrofit.class);

            Call<TrailerResponse> trailersResponse =
                    moviesApiInterface.getListOfTrailers(movie_id_fromIntent, getString(R.string.api_key));

            if (trailersResponse != null) {
                trailersResponse.enqueue(new Callback<TrailerResponse>() {


                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        TrailerResponse trailerResponse = response.body();

                        if (trailerResponse != null && trailerResponse.getListOfTrailers() != null) {
                            List<Trailer> trailers = trailerResponse.getListOfTrailers();

                            trailersAdapter = new TrailersAdapter(getApplicationContext(), trailers);
                            trailerRecyclerView.setAdapter(trailersAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getReviews() {
        try {
            IAPIforRetrofit moviesApiInterface =
                    APIclient.getApiClient().create(IAPIforRetrofit.class);

            Call<ReviewsResponse> reviewsResponse =
                    moviesApiInterface.getListOfReviews(movie_id_fromIntent, getString(R.string.api_key));

            if (reviewsResponse != null) {
                reviewsResponse.enqueue(new Callback<ReviewsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ReviewsResponse> call,
                                           @NonNull Response<ReviewsResponse> response) {
                        ReviewsResponse reviewsResponse = response.body();

                        if (reviewsResponse != null && reviewsResponse.getListOfReviews() != null) {
                            List<Review> reviews = reviewsResponse.getListOfReviews();

                            reviewsAdapter = new ReviewsAdapter(reviews);
                            reviewRecyclerView.setAdapter(reviewsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ReviewsResponse> call,
                                          @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}