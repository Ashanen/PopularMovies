package com.example.android.popularmovies1.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Filip Zych on 17,June,2018
 */
public class NetworkConnectivity {
    public static boolean isInternetWorking(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() !=null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
