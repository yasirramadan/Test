package com.example.root.test.core.dagger.module;

import android.app.Application;

import com.example.root.test.backend.interceptor.AppIdInterceptor;
import com.example.root.test.backend.rest.endpoint.WeatherEndpoint;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NetworkModule {
    private String baseUrl;

    public NetworkModule(String mBaseUrl) {
        this.baseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient okhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);

        //logging interceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(httpLoggingInterceptor);

        //AppId interceptor adds appId header to all requests.
        AppIdInterceptor interceptor = new AppIdInterceptor();
        client.addInterceptor(interceptor);

        return client.build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(ObjectMapper mapper, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

    @Provides
    @Singleton
    ObjectMapper ObjectMapper() {
        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Provides
    WeatherEndpoint weatherEndpoint(Retrofit retrofit) {
        return retrofit.create(WeatherEndpoint.class);
    }
}
