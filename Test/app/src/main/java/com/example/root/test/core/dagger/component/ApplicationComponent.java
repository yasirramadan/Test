package com.example.root.test.core.dagger.component;

import com.example.root.test.App;
import com.example.root.test.core.dagger.module.ActivityModule;
import com.example.root.test.core.dagger.module.AppModule;
import com.example.root.test.core.dagger.module.NetworkModule;
import com.example.root.test.fragment.weather.dagger.TabComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {

    // Application wide singletons.
    void inject(App application);

    TabComponent tabComponent(ActivityModule activityModule);
}