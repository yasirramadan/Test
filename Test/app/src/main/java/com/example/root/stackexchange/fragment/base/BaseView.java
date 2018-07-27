package com.example.root.stackexchange.fragment.base;

import android.support.annotation.StringRes;

import eu.inloop.viewmodel.IView;

public interface BaseView extends IView {
    /**
     * Shows toast.
     *
     * @param resourceId Text to show
     * @param duration   Duration (Toast.LENGTH_LONG, Toast.LENGTH_SHORT).
     */
    void showToast(@StringRes int resourceId, int duration);

    void showToast(String text, int duration);

    /**
     * shows loading
     */
    void showLoading();

    /**
     * dismisses loading
     */
    void dismissLoading();
}
