package com.example.root.test.fragment.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.ViewModelHelper;

/**
 * Base for all fragments using ViewModel pattern.
 */
public abstract class BaseViewModelFragment<T extends BaseView, R extends BaseViewModel<T>> extends BaseFragment implements IView {

    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(getActivity(), savedInstanceState, this::createViewModel, getArguments());
    }

    @Nullable
    public abstract R createViewModel();

    /**
     * Call this after your view is ready - usually on the end of {@link android.support.v4.app.Fragment#onViewCreated(View, Bundle)}}
     *
     * @param view view
     */
    protected void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    public void onDestroyView() {
        mViewModeHelper.onDestroyView(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @NonNull
    @SuppressWarnings("unused")
    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }
}