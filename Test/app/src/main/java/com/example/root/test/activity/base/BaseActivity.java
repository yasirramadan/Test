package com.example.root.test.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.root.test.R;

import eu.inloop.viewmodel.IViewModelProvider;
import eu.inloop.viewmodel.ViewModelProvider;

public abstract class BaseActivity extends AppCompatActivity implements IViewModelProvider {
    public static final String ROOT_FRAGMENT_TAG = "root_fragment";
    private ViewModelProvider viewModelProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelProvider = ViewModelProvider.newInstance(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isFinishing()){
            viewModelProvider.removeAllViewModels();
        }
    }

    @Nullable
    @Override
    public ViewModelProvider getViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = ViewModelProvider.newInstance(this);
        }
        return viewModelProvider;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return viewModelProvider;
    }

    /**
     * Will replace existing fragment by new one.
     *
     * @param fragmentClass  target class
     * @param bundle         bundle which will be available in new fragment
     * @param addToBackStack allow add swap fragment operation to back stack which allow go back after back button
     */
    public <T extends Fragment> T replaceFragment(Class<T> fragmentClass, Bundle bundle, boolean addToBackStack, int fragmentTransition) {
        final FragmentManager fm = getSupportFragmentManager();
        T fragment;
        try {
            fragment = fragmentClass.newInstance();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            FragmentTransaction ft = fm.beginTransaction();
            ft.setTransition(fragmentTransition);
            ft.replace(R.id.container, fragment, ROOT_FRAGMENT_TAG);
            if (addToBackStack) {
                ft.addToBackStack(fragmentClass.getName());
            }
            ft.commit();
            return fragment;
        } catch (Exception e) {
            Log.e("replaceFragment failed", e.getMessage());
        }
        return null;
    }


}