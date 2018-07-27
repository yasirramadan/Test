package com.example.root.stackexchange;

import android.app.Application;

import com.example.root.stackexchange.core.dagger.component.ApplicationComponent;
import com.example.root.stackexchange.core.dagger.component.DaggerApplicationComponent;
import com.example.root.stackexchange.core.dagger.module.AppModule;
import com.example.root.stackexchange.core.dagger.module.NetworkModule;

import static com.example.root.stackexchange.Constants.BASE_URL;

public class App extends Application {
    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BASE_URL))
                .build();
    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }
}