package com.example.root.test.fragment.tab_one;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.test.fragment.base.BaseViewModel;

import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class TabOneViewModel extends BaseViewModel<TabOneView> {
    @SaveState
    TabOneModel model;

    public static TabOneViewModel newInstance() {
        return new TabOneViewModel();
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        if (model == null) {
            model = new TabOneModel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    public void sendMoney(){
        //TODO :call backend
    }

    public TabOneModel getModel() {
        return model;
    }
}
