package com.example.root.test.fragment.weather.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.root.test.R;
import com.example.root.test.backend.rest.model.Main;
import com.example.root.test.fragment.base.BaseViewModel;
import com.example.root.test.service.WeatherService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class WeatherDetailViewModel extends BaseViewModel<WeatherDetailView> {
    public static final String ARG_CITY_NAME = "city_name";

    @SaveState
    WeatherDetailModel model;

    private final WeatherService weatherService;

    @Inject
    WeatherDetailViewModel(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        String cityName;
        if (arguments != null) {
            cityName = arguments.getString(ARG_CITY_NAME);
        } else {
            throw new IllegalArgumentException("cannot proceed without city name");
        }

        if (model == null) {
            model = new WeatherDetailModel(cityName);
        }
    }

    @Override
    public void onBindView(@NonNull WeatherDetailView view) {
        super.onBindView(view);
        //TODO: refactor: it is note a good idea to decide absence of data from one property but i do it for know because of lak of time.
        if (model.getHumidity() == null || model.getHumidity().get() == null) {
            getWeatherDetail(model.getCityName().get());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    /**
     * gets current weather for a given city.
     *
     * @param cityName
     */
    @SuppressLint("CheckResult")
    private void getWeatherDetail(String cityName) {
        weatherService.getWeatherCityDetailByCityName(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (getView() != null) {
                        getView().showLoading();
                    }
                })
                .doFinally(() -> {
                    if (getView() != null) {
                        getView().dismissLoading();
                    }
                })
                .subscribe(cityCurrentWeatherDetail -> {
                    //TODO : find better way to fill the model here i need to do a lot of null checks but also for lak of time i will not do it.
                    if (cityCurrentWeatherDetail != null) {
                        final Main main = cityCurrentWeatherDetail.getMain();

                        final Integer humidity = main.getHumidity();
                        model.setHumidity(String.valueOf(humidity));

                        final Integer pressure = main.getPressure();
                        model.setPressure(String.valueOf(pressure));

                        final Double temp = main.getTemp();
                        model.setTemperature(String.valueOf(temp));
                    }
                }, throwable -> {
                    Log.e("getWeatherForecastError", throwable.getMessage());

                    requestShowToast(R.string.general_error, Toast.LENGTH_SHORT);
                });
    }

    public WeatherDetailModel getModel() {
        return model;
    }
}