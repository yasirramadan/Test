package com.example.root.test.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eu.inloop.viewmodel.IViewModelProvider;
import eu.inloop.viewmodel.ViewModelProvider;

public abstract class BaseActivity extends AppCompatActivity implements IViewModelProvider {
    private ViewModelProvider viewModelProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelProvider = ViewModelProvider.newInstance(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isFinishing()){
            viewModelProvider.removeAllViewModels();
        }
    }

    @Nullable
    @Override
    public ViewModelProvider getViewModelProvider() {
        return viewModelProvider;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return viewModelProvider;
    }

}