package com.example.root.test.service;

//TODO: service should be injected by dagger per fragment

import android.location.Location;

import com.example.root.test.backend.rest.endpoint.WeatherEndpoint;
import com.example.root.test.backend.rest.model.WeatherForecast;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * service which will be used by view model to achieve backend calls.
 */
public class WeatherService {

    final private WeatherEndpoint weatherEndpoint;

    @Inject
    public WeatherService(WeatherEndpoint weatherEndpoint) {
        this.weatherEndpoint = weatherEndpoint;
    }

    public Single<WeatherForecast> getWeatherForecastByLocation(Location location) {
        Map<String, String> locationMap = new HashMap<>();
        locationMap.put("lat", String.valueOf(location.getLatitude()));
        locationMap.put("lon", String.valueOf(location.getLongitude()));

        return weatherEndpoint.getWeatherForeCastByLocation(locationMap);
    }
}