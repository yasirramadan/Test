package com.example.root.stackexchange.core.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.example.root.stackexchange.core.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity context) {
        this.activity = context;
    }

    @Provides
    @PerActivity
    Context providesContext() {
        return activity;
    }
}
