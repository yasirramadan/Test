
package com.example.root.test.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cod",
        "message",
        "cnt",
        "weatherList",
        "city"
})
public class WeatherForecast {

    @JsonProperty("cod")
    private String cod;
    @JsonProperty("message")
    private Double message;
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("weatherList")
    private List<WeatherList> weatherList = null;
    @JsonProperty("city")
    private City city;

    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    @JsonProperty("message")
    public Double getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Double message) {
        this.message = message;
    }

    @JsonProperty("cnt")
    public Integer getCnt() {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @JsonProperty("weatherList")
    public List<WeatherList> getWeatherList() {
        return weatherList;
    }

    @JsonProperty("weatherList")
    public void setWeatherList(List<WeatherList> weatherList) {
        this.weatherList = weatherList;
    }

    @JsonProperty("city")
    public City getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(City city) {
        this.city = city;
    }
}
