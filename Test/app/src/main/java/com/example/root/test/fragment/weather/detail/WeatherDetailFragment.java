package com.example.root.test.fragment.weather.detail;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test.R;
import com.example.root.test.fragment.base.BaseViewModelFragment;

public class WeatherDetailFragment extends BaseViewModelFragment<WeatherDetailView, WeatherDetailViewModel>
        implements WeatherDetailView {

    public static Fragment newInstance() {
        return new WeatherDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBindingUtil.inflate(inflater, R.layout.weather_detail_fragment, container, false);
        //binding.setModel(getViewModel().getModel());
        //binding.setViewModel(getViewModel());

        return null;
        //return binding.getRoot();
    }

    @Nullable
    @Override
    public WeatherDetailViewModel createViewModel() {
        return WeatherDetailViewModel.newInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }
}