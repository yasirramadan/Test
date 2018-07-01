package com.example.root.test.backend.rest.endpoint;

import com.example.root.test.backend.rest.model.WeatherForecast;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherEndpoint {
    /**
     * gets weather for a given number of cities
     *
     * @param params
     * @return
     */
    @GET("find")
    Single<WeatherForecast> getCitesWeatherByLocation(@QueryMap Map<String, String> params);

    /**
     * gets current weather for a given city name
     *
     * @param params
     * @return
     */
    @GET("weather")
    Single<WeatherForecast> getCurrentCityWeatherByCityName(@QueryMap Map<String, String> params);
}