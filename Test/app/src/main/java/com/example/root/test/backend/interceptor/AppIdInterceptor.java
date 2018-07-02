package com.example.root.test.backend.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * adds appId header foreach request.
 */
public class AppIdInterceptor implements Interceptor {
    private static final String APP_ID_HEADER = "appid";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(APP_ID_HEADER, "639de96bc230b7bd8bc52405513d1d1a")
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}