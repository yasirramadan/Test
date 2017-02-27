package com.example.root.test.core.dagger.component;

import com.example.root.test.activity.TabbedActivity;
import com.example.root.test.core.dagger.module.AppModule;
import com.example.root.test.core.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(TabbedActivity activity);
}