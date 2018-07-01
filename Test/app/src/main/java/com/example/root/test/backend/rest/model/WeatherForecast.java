
package com.example.root.test.backend.rest.model;

import com.example.CityWeather;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cod",
        "message",
        "cnt",
        "forecast",
        "city"
})
public class WeatherForecast implements Serializable {

    @JsonProperty("message")
    private String message;
    @JsonProperty("cod")
    private String cod;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("list")
    private java.util.List<CityWeather> cityWeathers = null;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("list")
    public java.util.List<CityWeather> getCityWeathers() {
        return cityWeathers;
    }

    @JsonProperty("list")
    public void setCityWeathers(List<CityWeather> list) {
        this.cityWeathers = list;
    }
}
