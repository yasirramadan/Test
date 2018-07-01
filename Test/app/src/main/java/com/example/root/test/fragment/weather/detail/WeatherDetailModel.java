package com.example.root.test.fragment.weather.detail;

import android.databinding.ObservableField;

import java.io.Serializable;

//TODO : not all properties are included because of lak of time.
public class WeatherDetailModel implements Serializable {
    /**
     * city name
     */
    private ObservableField<String> cityName = new ObservableField<>();

    /**
     * Weather temperature
     */
    private ObservableField<String> temperature = new ObservableField<>();

    /**
     * Weather pressure
     */
    private ObservableField<String> pressure = new ObservableField<>();

    /**
     * Weather humidity
     */
    private ObservableField<String> humidity = new ObservableField<>();

    public WeatherDetailModel(String cityName) {
        this.cityName.set(cityName);
    }

    public ObservableField<String> getTemperature() {
        return temperature;
    }

    public ObservableField<String> getPressure() {
        return pressure;
    }

    public ObservableField<String> getHumidity() {
        return humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature.set(temperature);
    }

    public void setPressure(String pressure) {
        this.pressure.set(pressure);
    }

    public void setHumidity(String humidity) {
        this.humidity.set(humidity);
    }

    public ObservableField<String> getCityName() {
        return cityName;
    }
}
