package com.example.root.stackexchange.fragment.base;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import eu.inloop.viewmodel.AbstractViewModel;

public class BaseViewModel<V extends BaseView> extends AbstractViewModel<V> {
    /**
     * @return current activity
     */
    @Nullable
    protected Activity getActivity() {
        if (getView() != null && getView() instanceof BaseFragment) {
            final BaseFragment fragment = (BaseFragment) getView();
            return fragment.getActivity();
        } else if (getView() != null && getView() instanceof Activity) {
            return (Activity) getView();
        }
        return null;
    }

    protected void requestShowToast(@StringRes final int res, final int length) {
        if (getView() != null) {
            getView().showToast(res, length);
        }
    }

    protected void requestShowToast(final String text, final int length) {
        if (getView() != null) {
            getView().showToast(text, length);
        }
    }
}
