package com.example.root.stackexchange.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.root.stackexchange.activity.MainActivity;

public abstract class BaseFragment extends Fragment implements BaseView {
    private static final String ARG_TOOLBAR_TITLE = "ARG_FRAGMENT_TOOLBAR_TITLE";

    private Integer fragmentTitleResourceId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getParentFragment() == null) {
            this.setRetainInstance(true);
        }

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(ARG_TOOLBAR_TITLE)) {
                fragmentTitleResourceId = savedInstanceState.getInt(ARG_TOOLBAR_TITLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_TOOLBAR_TITLE, fragmentTitleResourceId);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragmentTitle();
    }

    @Override
    public void showToast(int resourceId, int duration) {
        showToast(getString(resourceId), duration);
    }

    @Override
    public void showToast(String text, int duration) {
        Toast.makeText(getActivity(), text, duration).show();
    }

    /**
     * Updates toolbar title.
     */
    private void updateFragmentTitle() {
        if (fragmentTitleResourceId == null) {
            return;
        }

        if (getActivity() != null && getActivity() instanceof MainActivity) {
            final MainActivity activity = (MainActivity) getActivity();
            if (fragmentTitleResourceId != null) {
                activity.setToolbarTitle(fragmentTitleResourceId);
            }
        }
    }

    protected final void setFragmentTitle(@Nullable final Integer titleResId) {
        fragmentTitleResourceId = titleResId;

        if (isVisible()) {
            updateFragmentTitle();
        }
    }
}