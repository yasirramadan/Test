package com.example.root.test.fragment.weather.list;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test.App;
import com.example.root.test.R;
import com.example.root.test.core.dagger.module.ActivityModule;
import com.example.root.test.fragment.base.BaseViewModelFragment;

public class WeatherListFragment extends BaseViewModelFragment<WeatherListView, WeatherListViewModel>
        implements WeatherListView {

    public static Fragment newInstance() {
        return new WeatherListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBindingUtil.inflate(inflater, R.layout.weather_list_fragment, container, false);
        return null;
    }

    @Nullable
    @Override
    public WeatherListViewModel createViewModel() {
        return App.getAppComponent().tabComponent(new ActivityModule(getActivity())).tabOneViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }
}