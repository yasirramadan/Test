package com.example.root.stackexchange.fragment.questions.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.stackexchange.fragment.base.BaseViewModel;
import com.example.root.stackexchange.service.StackExchangeService;

import javax.inject.Inject;

import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class QuestionDetailViewModel extends BaseViewModel<QuestionDetailView> {
    public static final String ARG_QUESTION = "arg_question";

    @SaveState
    QuestionDetailModel model;

    private final StackExchangeService stackExchangeService;

    @Inject
    QuestionDetailViewModel(StackExchangeService stackExchangeService) {
        this.stackExchangeService = stackExchangeService;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        String cityName = null;
        if (arguments != null) {
            arguments.getString(ARG_QUESTION);
        } else {
            throw new IllegalArgumentException("cannot proceed without city name");
        }

        if (model == null) {
            model = new QuestionDetailModel(cityName);
        }
    }

    @Override
    public void onBindView(@NonNull QuestionDetailView view) {
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

    }

    public QuestionDetailModel getModel() {
        return model;
    }
}