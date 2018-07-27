package com.example.root.stackexchange.fragment.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getParentFragment() == null) {
            this.setRetainInstance(true);
        }
    }

    @Override
    public void showToast(int resourceId, int duration) {
        showToast(getString(resourceId), duration);
    }

    @Override
    public void showToast(String text, int duration) {
        Toast.makeText(getActivity(), text, duration).show();
    }
}