package com.example.root.test.fragment.weather.list;


import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.root.test.R;
import com.example.root.test.fragment.base.BaseViewModel;
import com.example.root.test.service.WeatherService;
import com.patloew.rxlocation.RxLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class WeatherListViewModel extends BaseViewModel<WeatherListView> {
    private final WeatherService weatherService;
    private final RxLocation rxLocation;

    @SaveState
    WeatherListModel model;

    @Inject
    WeatherListViewModel(WeatherService weatherService, RxLocation rxLocation) {
        this.weatherService = weatherService;
        this.rxLocation = rxLocation;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        if (model == null) {
            model = new WeatherListModel();
        }
    }

    @Override
    public void onBindView(@NonNull WeatherListView view) {
        super.onBindView(view);
        if (model.getCitiesWeather() == null || model.getCitiesWeather().size() == 0) {
            getWeatherForeCast();
        } else {
            if (getView() != null) {
                getView().show(model.getCitiesWeather());
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    /**
     * gets weather forecast for five days.
     */
    @SuppressLint({"CheckResult", "MissingPermission"})
    private void getWeatherForeCast() {
        new RxPermissions(getActivity())
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .singleOrError()
                .flatMap(granted -> {
                    if (granted) {
                        return rxLocation
                                .location()
                                .lastLocation()
                                .toSingle();
                    } else {
                        return Single.error(new LocationPermissionException());
                    }
                }).flatMap(location -> weatherService.getCitesWeatherByLocation(location, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (getView() != null) {
                        getView().showLoading();
                    }
                })).doFinally(() -> {
            if (getView() != null) {
                getView().dismissLoading();
            }
        })
                .subscribe(weatherForecast -> {
                    if (getView() != null) {
                        model.setCitesWeather(weatherForecast.getCityWeathers());
                        getView().show(model.getCitiesWeather());
                    }
                }, throwable -> {
                    Log.d("getWeatherError", throwable.getMessage());

                    if (throwable instanceof LocationPermissionException) {
                        requestShowToast(R.string.location_permission_error, Toast.LENGTH_SHORT);
                    } else {
                        requestShowToast(R.string.general_error, Toast.LENGTH_SHORT);
                    }
                });
    }

    private class LocationPermissionException extends Exception {

    }
}