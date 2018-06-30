package com.example.root.test.fragment.weather.list;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.test.fragment.base.BaseViewModel;
import com.example.root.test.service.ExampleService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class WeatherListViewModel extends BaseViewModel<WeatherListView> {
    private ExampleService exampleService;

    @SaveState
    WeatherListModel model;

    @Inject
    WeatherListViewModel(ExampleService exampleService) {
        this.exampleService = exampleService;
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
        getExample();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    /**
     * gets data from data source
     */
    private void getExample() {
        exampleService.getExample()
                .doOnSubscribe(disposable -> {
                    //TODO:show loading to be defined in BaseView
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    //TODO:dismiss loading to be defined in BaseView
                })
                .subscribe(objects -> {
                            //TODO: fill model.
                        },
                        throwable -> {
                            //TODO: show error to be defined in BaseView
                        });
    }

    public void sendMoney() {

    }

    public WeatherListModel getModel() {
        return model;
    }
}
