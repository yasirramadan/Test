package com.example.root.stackexchange.core.dagger.module;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.root.stackexchange.backend.exeption.RxErrorHandlingCallAdapterFactory;
import com.example.root.stackexchange.backend.interceptor.OfflineResponseCacheInterceptor;
import com.example.root.stackexchange.backend.interceptor.QuestionsDefaultHeadersInterceptor;
import com.example.root.stackexchange.backend.rest.endpoint.QuestionsEndpoint;
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

    //TODO: move to networkUtil
    @Provides
    ConnectivityManager connectivityManager(Application application) {
      return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    QuestionsDefaultHeadersInterceptor interceptor() {
        return new QuestionsDefaultHeadersInterceptor();
    }

    @Provides
    OfflineResponseCacheInterceptor offlineResponseCacheInterceptor(ConnectivityManager connectivityManager) {
        return new OfflineResponseCacheInterceptor(connectivityManager);
    }

    @Provides
    @Singleton
    OkHttpClient okhttpClient(Cache cache,
                              QuestionsDefaultHeadersInterceptor interceptor, OfflineResponseCacheInterceptor offlineResponseCacheInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);

        client.addInterceptor(offlineResponseCacheInterceptor);

        //default headers interceptor
        client.addInterceptor(interceptor);

        //logging interceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(httpLoggingInterceptor);

        return client.build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(ObjectMapper mapper, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

    @Provides
    @Singleton
    ObjectMapper ObjectMapper() {
        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Provides
    @Singleton
    QuestionsEndpoint weatherEndpoint(Retrofit retrofit) {
        return retrofit.create(QuestionsEndpoint.class);
    }
}
