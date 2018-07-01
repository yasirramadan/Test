package com.example.root.test.fragment.weather.list;

import com.example.CityWeather;
import com.example.root.test.fragment.base.BaseView;

import java.util.List;

public interface WeatherListView extends BaseView {
    void show(List<CityWeather> forecast);
}
