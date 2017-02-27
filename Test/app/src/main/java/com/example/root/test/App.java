package com.example.root.test;

import android.app.Application;

import com.example.root.test.core.dagger.component.DaggerNetworkComponent;
import com.example.root.test.core.dagger.component.NetworkComponent;
import com.example.root.test.core.dagger.module.AppModule;
import com.example.root.test.core.dagger.module.NetworkModule;

public class App extends Application {
    private NetworkComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://www.somethinge.com/"))
                .build();
    }

    public NetworkComponent getNetComponent() {
        return mNetComponent;
    }
}