package com.example.root.test;

import android.app.Application;

import com.example.root.test.core.dagger.component.ApplicationComponent;
import com.example.root.test.core.dagger.component.DaggerApplicationComponent;
import com.example.root.test.core.dagger.module.AppModule;
import com.example.root.test.core.dagger.module.NetworkModule;

public class App extends Application {
    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://www.somethinge.com/"))
                .build();

        //appComponent.inject(this);
    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }
}