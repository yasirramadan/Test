package com.example.root.test.fragment.tabs.tab_one;


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
import com.example.root.test.databinding.TabOneFragmentBinding;
import com.example.root.test.fragment.base.BaseViewModelFragment;

public class TabOneFragment extends BaseViewModelFragment<TabOneView, TabOneViewModel>
        implements TabOneView {
    TabOneFragmentBinding binding;

    public static Fragment newInstance() {
        return new TabOneFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          binding = DataBindingUtil.inflate(inflater, R.layout.tab_one_fragment, container, false);
          binding.setModel(getViewModel().getModel());
          binding.setViewModel(getViewModel());
        return null;
        //      return binding.getRoot();
    }

    @Nullable
    @Override
    public TabOneViewModel createViewModel() {
        return App.getAppComponent().tabComponent(new ActivityModule(getActivity())).tabOneViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }
}