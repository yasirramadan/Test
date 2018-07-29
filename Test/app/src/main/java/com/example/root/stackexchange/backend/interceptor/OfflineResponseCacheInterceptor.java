package com.example.root.stackexchange.backend.interceptor;

import android.content.Context;

import com.example.root.stackexchange.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineResponseCacheInterceptor implements Interceptor {

    private final Context context;

    public OfflineResponseCacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtils.isNetworkOffline(context)) {
            request = request.newBuilder()
                    .header("Cache-Control",
                            "public, only-if-cached, max-stale=" + 2419200)
                    .build();
        }
        return chain.proceed(request);
    }
}