package com.example.root.stackexchange.backend.interceptor;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineResponseCacheInterceptor implements Interceptor {

    private final ConnectivityManager connectivityManager;

    public OfflineResponseCacheInterceptor(ConnectivityManager manager) {
        this.connectivityManager = manager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnectedOrConnecting()) {
            request = request.newBuilder()
                    .header("Cache-Control",
                            "public, only-if-cached, max-stale=" + 2419200)
                    .build();
        }
        return chain.proceed(request);
    }
}