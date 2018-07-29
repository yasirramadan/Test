package com.example.root.stackexchange.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.root.stackexchange.backend.exeption.BackendException;

public class NetworkUtils {
    public static boolean isNetworkOffline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager != null ? manager.getActiveNetworkInfo() : null;
        return info == null || !info.isConnectedOrConnecting();
    }

    public static BackendException.Kind getExceptionKind(Throwable throwable){
       if (throwable != null && throwable instanceof BackendException){
           return ((BackendException)throwable).getKind();
       }

       return BackendException.Kind.UNEXPECTED;
    }
}