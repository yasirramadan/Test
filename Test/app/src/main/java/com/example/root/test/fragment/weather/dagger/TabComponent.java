package com.example.root.test.fragment.weather.dagger;

import com.example.root.test.core.dagger.module.ActivityModule;
import com.example.root.test.fragment.weather.list.WeatherListViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface TabComponent {
    WeatherListViewModel tabOneViewModel();
}