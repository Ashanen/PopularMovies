package com.example.android.popularmovies1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies1.APIcontroler.APIclient;
import com.example.android.popularmovies1.APIcontroler.IAPIforRetrofit;
import com.example.android.popularmovies1.Adapters.FavouritesAdapter;
import com.example.android.popularmovies1.Adapters.MovieAdapter;
import com.example.android.popularmovies1.Database.FavouriteMovie;
import com.example.android.popularmovies1.Fragments.FavouritesFragment;
import com.example.android.popularmovies1.Fragments.PopularMoviesFragment;
import com.example.android.popularmovies1.Fragments.TopRatedMoviesFragment;
import com.example.android.popularmovies1.Fragments.UpcomingMoviesFragment;
import com.example.android.popularmovies1.Models.DatabaseJSONresponse;
import com.example.android.popularmovies1.Models.FavouriteMoviesViewModel;
import com.example.android.popularmovies1.Models.Movies;
import com.example.android.popularmovies1.Utils.ConstantValues;
import com.example.android.popularmovies1.Utils.NetworkConnectivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.popularmovies1.Utils.ConstantValues.API_KEY;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private List<FavouriteMovie> favouriteMoviesNewList;
    private List<Movies> movieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        FavouriteMoviesViewModel favouriteMoviesViewModel =
                ViewModelProviders.of(this).get(FavouriteMoviesViewModel.class);


        favouriteMoviesViewModel.getmAllFavourites().observe(this, new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(@Nullable List<FavouriteMovie> favouriteMovies) {
                favouriteMoviesNewList = favouriteMovies;
            }
        });
    }

    public void getPopularMovies(String preferred) {
        if (!NetworkConnectivity.isInternetWorking(getApplicationContext())) {
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        } else {
            try {
                if (ConstantValues.API_KEY.isEmpty()) {
                    Toast.makeText(this, getString(R.string.provide_api_key), Toast.LENGTH_LONG).show();
                } else {
                    IAPIforRetrofit apiInterface = APIclient.getApiClient().create(IAPIforRetrofit.class);


                    Call<DatabaseJSONresponse> moviesResult = null;

                    if (preferred.equals(getString(R.string.most_popular_key))) {
                        moviesResult = apiInterface.getPopularMovies(API_KEY);
                    } else if (preferred.equals(getString(R.string.top_rated_key))) {
                        moviesResult = apiInterface.getTopRatedMovies(API_KEY);
                    } else if (preferred.equals(getString(R.string.upcoming_key))) {
                        moviesResult = apiInterface.getUpcomingMovies(API_KEY);
                    }


                    if (moviesResult != null) {
                        moviesResult.enqueue(new Callback<DatabaseJSONresponse>() {
                            @Override
                            public void onResponse(@NonNull Call<DatabaseJSONresponse> call, @NonNull Response<DatabaseJSONresponse> response) {
                                List<Movies> movies = response.body().getResults();
                                recyclerView.setAdapter(new MovieAdapter(movies, getApplicationContext()));
                            }

                            @Override
                            public void onFailure(Call<DatabaseJSONresponse> call, Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void openFavourites() {
        recyclerView = findViewById(R.id.recyclerView);
        FavouritesAdapter moviesAdapter = new FavouritesAdapter(this, favouriteMoviesNewList);

        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();
    }


    private void init() {
        recyclerView = findViewById(R.id.recyclerView);

        MovieAdapter moviesAdapter = new MovieAdapter(movieList, this);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();

        getPopularMovies(getString(R.string.most_popular_key));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment selectedFragment = null;

        int movie_sort_type_id = item.getItemId();

        switch (movie_sort_type_id) {
            case R.id.menu_sort_top_rated:
                selectedFragment = TopRatedMoviesFragment.newInstance();
                getPopularMovies(getString(R.string.top_rated_key));
                break;

            case R.id.menu_sort_popularity:
                selectedFragment = PopularMoviesFragment.newInstance();
                getPopularMovies(getString(R.string.most_popular_key));
                break;
            case R.id.menu_sort_upcoming:
                selectedFragment = UpcomingMoviesFragment.newInstance();
                getPopularMovies(getString(R.string.upcoming_key));
                break;
            case R.id.menu_sort_fav:
                selectedFragment = FavouritesFragment.newInstance();
                openFavourites();
                break;
        }

        if (selectedFragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_for_movie, selectedFragment);
            fragmentTransaction.commit();
        }
        return true;
    }
}

