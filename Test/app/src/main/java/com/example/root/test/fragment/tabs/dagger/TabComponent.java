package com.example.root.test.fragment.tabs.dagger;

import com.example.root.test.core.dagger.module.ActivityModule;
import com.example.root.test.fragment.tabs.tab_one.TabOneViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface TabComponent {
    TabOneViewModel tabOneViewModel();
}