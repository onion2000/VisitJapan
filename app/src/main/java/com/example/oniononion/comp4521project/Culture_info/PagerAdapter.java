package com.example.oniononion.comp4521project.Culture_info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ylcheung on 18/5/16.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return CultureFragment.newInstance(CultureType.PEOPLE);
            case 1:
                return CultureFragment.newInstance(CultureType.RELIGION);
            case 2:
                return CultureFragment.newInstance(CultureType.SOCIAL);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}