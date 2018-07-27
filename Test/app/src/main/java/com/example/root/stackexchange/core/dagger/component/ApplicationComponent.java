package com.example.root.stackexchange.core.dagger.component;

import com.example.root.stackexchange.App;
import com.example.root.stackexchange.core.dagger.module.ActivityModule;
import com.example.root.stackexchange.core.dagger.module.AppModule;
import com.example.root.stackexchange.core.dagger.module.NetworkModule;
import com.example.root.stackexchange.fragment.questions.dagger.QuestionComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {

    // Application wide singletons.
    void inject(App application);

    QuestionComponent questionComponent(ActivityModule activityModule);
}