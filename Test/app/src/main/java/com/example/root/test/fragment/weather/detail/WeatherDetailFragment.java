package com.example.root.test.fragment.weather.detail;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test.App;
import com.example.root.test.R;
import com.example.root.test.activity.base.BaseActivity;
import com.example.root.test.core.dagger.module.ActivityModule;
import com.example.root.test.databinding.WeatherDetailFragmentBinding;
import com.example.root.test.fragment.base.BaseViewModelFragment;

import static com.example.root.test.fragment.weather.detail.WeatherDetailViewModel.ARG_CITY_NAME;

public class WeatherDetailFragment extends BaseViewModelFragment<WeatherDetailView, WeatherDetailViewModel>
        implements WeatherDetailView {

    WeatherDetailFragmentBinding binding;

    public static void navigate(String ciyName, Activity activity) {
        Bundle bundle = new Bundle(1);
        bundle.putString(ARG_CITY_NAME, ciyName);

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).replaceFragment(WeatherDetailFragment.class, bundle, true, 0);
        }
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
        return App.getAppComponent().weatherComponent(new ActivityModule(getActivity())).weatherDetailViewModel();
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