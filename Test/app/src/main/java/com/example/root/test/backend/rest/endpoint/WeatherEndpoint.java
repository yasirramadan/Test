package com.example.root.test.backend.rest.endpoint;

import com.example.root.test.backend.rest.model.WeatherForecast;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherEndpoint {
    @GET("forecast")
    Single<WeatherForecast> getWeatherForeCastByLocation(@QueryMap Map<String, String> location);
}