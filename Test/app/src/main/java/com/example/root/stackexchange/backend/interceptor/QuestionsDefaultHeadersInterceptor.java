package com.example.root.stackexchange.backend.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class QuestionsDefaultHeadersInterceptor implements Interceptor {
    private static final String FILTER = "filter";
    private static final String ORDER = "order";
    private static final String SORT = "sort";
    private static final String SITE = "site";
    private static final String PAGE_SIZE = "pagesize";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(FILTER, "withBody")
                .addQueryParameter(ORDER, "desc")
                .addQueryParameter(SORT, "creation")
                .addQueryParameter(SITE, "cooking")
                .addQueryParameter(PAGE_SIZE, "5")
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}