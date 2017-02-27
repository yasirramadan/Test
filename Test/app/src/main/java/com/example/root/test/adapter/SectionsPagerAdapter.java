package com.example.root.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.root.test.fragment.tab_four.TabFourFragment;
import com.example.root.test.fragment.tab_one.TabOneFragment;
import com.example.root.test.fragment.tab_three.TabThreeFragment;
import com.example.root.test.fragment.tab_two.TabTwoFragment;

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
            case 2:
                return TabThreeFragment.newInstance();
            case 3:
                return TabFourFragment.newInstance();
            default:
                return new TabOneFragment().newInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TAB ONE";
            case 1:
                return "TAB TWO";
            case 2:
                return "TAB THREE";
            case 3:
                return "TAB FOUR";
        }
        return null;
    }
}