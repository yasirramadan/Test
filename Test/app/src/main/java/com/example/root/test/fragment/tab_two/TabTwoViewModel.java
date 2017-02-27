package com.example.root.test.fragment.tab_two;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.test.fragment.base.BaseViewModel;

import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class TabTwoViewModel extends BaseViewModel<TabTwoView> {

    @SaveState
    TabTwoModel model;

    public static TabTwoViewModel newInstance() {
        return new TabTwoViewModel();
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        if (model == null){
            model = new TabTwoModel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    public void requestMoney(){
       //TODO : make service that will call api
   }

    public TabTwoModel getModel() {
        return model;
    }
}