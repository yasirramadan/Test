package com.example.root.test.fragment.tab_three;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test.R;
import com.example.root.test.databinding.TabThreeFragmentBinding;
import com.example.root.test.fragment.base.BaseViewModelFragment;

public class
TabThreeFragment extends BaseViewModelFragment<TabThreeView, TabThreeViewModel>
        implements TabThreeView {

    TabThreeFragmentBinding binding;

    public static Fragment newInstance() {
        return new TabThreeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_three_fragment, container, false);

        return binding.getRoot();
    }

    @Nullable
    @Override
    public TabThreeViewModel createViewModel() {
        return TabThreeViewModel.newInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }
}