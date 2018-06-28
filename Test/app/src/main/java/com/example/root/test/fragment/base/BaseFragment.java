package com.example.root.test.fragment.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getParentFragment() == null) {
            this.setRetainInstance(true);
        }
    }
}