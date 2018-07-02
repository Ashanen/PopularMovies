package com.example.android.popularmovies1.APIcontroler;

import com.example.android.popularmovies1.Utils.ConstantValues;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip Zych on 13,April,2018
 */
public class APIclient {

    private static Retrofit retrofit;
    private static OkHttpClient.Builder baseOkHttpClientBuilder;

    public static Retrofit getApiClient() {

        if (retrofit == null) {

            okHttpClient();

            retrofit = new Retrofit.Builder().baseUrl(ConstantValues.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(baseOkHttpClientBuilder.build()).
                    build();

        }
        return retrofit;
    }


    private static void okHttpClient() {
        baseOkHttpClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        baseOkHttpClientBuilder.addInterceptor(logging);
        baseOkHttpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        baseOkHttpClientBuilder.readTimeout(15, TimeUnit.SECONDS);
        baseOkHttpClientBuilder.retryOnConnectionFailure(true);
    }
}
