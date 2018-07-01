package com.example.root.test.fragment.weather.list;

import android.databinding.ObservableArrayList;

import com.example.CityWeather;

import java.io.Serializable;
import java.util.List;

/**
 * This class is just example of model classes.
 */
public class WeatherListModel implements Serializable {
    /**
     * list which contains forecast info.
     */
    private ObservableArrayList<CityWeather> citiesWeather = new ObservableArrayList<>();

    public ObservableArrayList<CityWeather> getCitiesWeather() {
        return citiesWeather;
    }

    public void setCitesWeather(List<CityWeather> forecasts) {
        this.citiesWeather.addAll(forecasts);
    }
}
