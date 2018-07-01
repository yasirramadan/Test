package com.example.root.test.fragment.weather.list;

import com.example.root.test.fragment.base.BaseView;

public interface WeatherListView extends BaseView {
    void show(List<CityWeather> forecast);
}
