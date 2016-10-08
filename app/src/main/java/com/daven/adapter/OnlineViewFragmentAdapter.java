package com.daven.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daven.base.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daven on 26/09/2016.
 * FragmentPagerAdapter  是无状态的
 * FragmentStatePagerAdapter 这个只保留三个，当前页，前页，后页
 */
public class OnlineViewFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public OnlineViewFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
