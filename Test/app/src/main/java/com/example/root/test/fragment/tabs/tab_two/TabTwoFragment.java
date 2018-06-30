package com.example.root.test.fragment.tabs.tab_two;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test.fragment.base.BaseViewModelFragment;

public class TabTwoFragment extends BaseViewModelFragment<TabTwoView, TabTwoViewModel>
        implements TabTwoView {

   // TabTwoFragmentBinding binding;

    public static Fragment newInstance() {
        return new TabTwoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     //   binding = DataBindingUtil.inflate(inflater, R.layout.tab_two_fragment, container, false);
        //binding.setModel(getViewModel().getModel());
        //binding.setViewModel(getViewModel());

        return null;
        //return binding.getRoot();
    }

    @Nullable
    @Override
    public TabTwoViewModel createViewModel() {
        return TabTwoViewModel.newInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }
}