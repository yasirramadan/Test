package com.example.root.test.fragment.weather.detail;


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
import com.example.root.test.databinding.WeatherDetailFragmentBinding;
import com.example.root.test.fragment.base.BaseViewModelFragment;

public class WeatherDetailFragment extends BaseViewModelFragment<WeatherDetailView, WeatherDetailViewModel>
        implements WeatherDetailView {

    WeatherDetailFragmentBinding binding;
    public static Fragment newInstance() {
        return new WeatherDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_detail_fragment, container, false);
        binding.setModel(getViewModel().getModel());

        return binding.getRoot();
    }

    @Nullable
    @Override
    public WeatherDetailViewModel createViewModel() {
        return App.getAppComponent().tabComponent(new ActivityModule(getActivity())).weatherDetailViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void showLoading() {
        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        binding.progress.setVisibility(View.GONE);
    }
}