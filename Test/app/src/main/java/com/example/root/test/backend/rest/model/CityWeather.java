
package com.example;

import com.example.root.test.backend.rest.model.Clouds;
import com.example.root.test.backend.rest.model.Coord;
import com.example.root.test.backend.rest.model.Main;
import com.example.root.test.backend.rest.model.Sys;
import com.example.root.test.backend.rest.model.Weather;
import com.example.root.test.backend.rest.model.Wind;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "coord",
        "main",
        "dt",
        "wind",
        "sys",
        "rain",
        "snow",
        "clouds",
        "weather"
})
public class CityWeather implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("rain")
    private Object rain;
    @JsonProperty("snow")
    private Object snow;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("weather")
    private java.util.List<Weather> weather = null;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @JsonProperty("sys")
    public Sys getSys() {
        return sys;
    }

    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @JsonProperty("rain")
    public Object getRain() {
        return rain;
    }

    @JsonProperty("rain")
    public void setRain(Object rain) {
        this.rain = rain;
    }

    @JsonProperty("snow")
    public Object getSnow() {
        return snow;
    }

    @JsonProperty("snow")
    public void setSnow(Object snow) {
        this.snow = snow;
    }

    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    @JsonProperty("weather")
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

}
