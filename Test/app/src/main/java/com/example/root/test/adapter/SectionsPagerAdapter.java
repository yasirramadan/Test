package com.example.root.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.root.test.fragment.tabs.tab_one.TabOneFragment;
import com.example.root.test.fragment.tabs.tab_two.TabTwoFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TabOneFragment.newInstance();
            case 1:
                return TabTwoFragment.newInstance();
            default:
                return new TabOneFragment().newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TAB ONE";
            case 1:
                return "TAB TWO";
        }
        return null;
    }
}