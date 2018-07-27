package com.example.root.stackexchange.core.dagger.module;

import android.app.Application;
import android.content.Context;

import com.example.root.stackexchange.backend.rest.endpoint.QuestionsEndpoint;
import com.example.root.stackexchange.service.StackExchangeService;

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
    StackExchangeService stackExchangeService(QuestionsEndpoint questionsEndpoint) {
        return new StackExchangeService(questionsEndpoint);
    }
}