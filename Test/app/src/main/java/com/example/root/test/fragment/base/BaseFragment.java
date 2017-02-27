package com.example.root.test.fragment.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getParentFragment() == null) {
            this.setRetainInstance(true);
        }
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return null;
    }

    @Override
    public void removeViewModel() {

    }

    protected View createView(@LayoutRes int layout, LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(layout, container, false);
        return view;
    }
}