package com.example.root.test.fragment.weather.list;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test.App;
import com.example.root.test.R;
import com.example.root.test.core.dagger.module.ActivityModule;
import com.example.root.test.databinding.WeatherListFragmentBinding;
import com.example.root.test.fragment.base.BaseViewModelFragment;

public class WeatherListFragment extends BaseViewModelFragment<WeatherListView, WeatherListViewModel>
        implements WeatherListView {
    WeatherListFragmentBinding binding;

    public static Fragment newInstance() {
        return new WeatherListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_list_fragment, container, false);

        //setup recycler view
        binding.forecasts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.forecasts.setHasFixedSize(true);

        return binding.getRoot();
    }

    @Nullable
    @Override
    public WeatherListViewModel createViewModel() {
        return App.getAppComponent().tabComponent(new ActivityModule(getActivity())).weatherListViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }
}