package com.example.root.test.fragment.weather.list;

import android.databinding.ObservableArrayList;

import com.example.root.test.backend.rest.model.Forecast;

import java.io.Serializable;
import java.util.List;

/**
 * This class is just example of model classes.
 */
public class WeatherListModel implements Serializable {
    /**
     * list which contains forecast info.
     */
    private ObservableArrayList<Forecast> forecasts = new ObservableArrayList<>();

    public ObservableArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts.addAll(forecasts);
    }
}
