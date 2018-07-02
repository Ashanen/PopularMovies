package com.example.android.popularmovies1.APIcontroler;

import com.example.android.popularmovies1.Models.DatabaseJSONresponse;
import com.example.android.popularmovies1.Models.ReviewsResponse;
import com.example.android.popularmovies1.Models.TrailerResponse;
import com.example.android.popularmovies1.Utils.ConstantValues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPIforRetrofit {

    /** The @GET annotation declares that this request uses the HTTP GET method. */

    @GET(ConstantValues.TOP_RATED)
    Call<DatabaseJSONresponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET(ConstantValues.POPULAR)
    Call<DatabaseJSONresponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET(ConstantValues.UPCOMING)
    Call<DatabaseJSONresponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET(ConstantValues.TRAILERS)
    Call<TrailerResponse> getListOfTrailers(@Path("movie_id") int id,
                                            @Query("api_key") String apiKey);

    @GET(ConstantValues.REVIEWS)
    Call<ReviewsResponse> getListOfReviews(@Path("movie_id") int id,
                                           @Query("api_key") String apiKey);
}
