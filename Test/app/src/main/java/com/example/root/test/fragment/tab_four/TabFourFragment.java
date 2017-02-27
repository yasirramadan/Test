package com.example.root.test.fragment.tab_four;


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
import com.example.root.test.fragment.tab_one.TabOneFragment;

public class TabFourFragment extends BaseViewModelFragment<TabFourView, TabFourViewModel>
        implements TabFourView {

    TabThreeFragmentBinding binding;

    public static Fragment newInstance() {
        return new TabFourFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.tab_four_fragment, container, false);

        return binding.getRoot();
    }

    @Nullable
    @Override
    public TabFourViewModel createViewModel() {
        return TabFourViewModel.newInstance();
    }
}