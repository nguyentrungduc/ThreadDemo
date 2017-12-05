package com.example.sony.threaddemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sony on 12/5/2017.
 */

public class NetWorkManager {
    private static NetWorkManager instance;
    public static NetWorkManager getInstance(){
        return instance;
    }

    public static void init(Context context){
        instance = new NetWorkManager(context);
    }


    private ConnectivityManager connectivityManager;

    private NetWorkManager(Context context) {
        this.connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnectedToInternet(){
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
