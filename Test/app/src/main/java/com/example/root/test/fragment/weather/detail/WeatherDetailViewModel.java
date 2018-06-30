package com.example.root.test.fragment.weather.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.test.fragment.base.BaseViewModel;

import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class WeatherDetailViewModel extends BaseViewModel<WeatherDetailView> {

    @SaveState
    WeatherDetailModel model;

    public static WeatherDetailViewModel newInstance() {
        return new WeatherDetailViewModel();
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        if (model == null) {
            model = new WeatherDetailModel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    public void requestMoney() {
        //TODO : make service that will call api
    }

    public WeatherDetailModel getModel() {
        return model;
    }
}