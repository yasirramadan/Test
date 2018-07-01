package com.example.root.test.core.dagger.module;

import android.app.Application;
import android.content.Context;

import com.example.root.test.backend.rest.endpoint.WeatherEndpoint;
import com.example.root.test.service.WeatherService;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @Named("applicationContext")
    Context context() {
        return application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    WeatherService exampleService(WeatherEndpoint weatherEndpoint) {
        return new WeatherService(weatherEndpoint);
    }

    @Provides
    @Singleton
    RxLocation rxLocation(@Named("applicationContext") Context context) {
        return new RxLocation(context);
    }
}