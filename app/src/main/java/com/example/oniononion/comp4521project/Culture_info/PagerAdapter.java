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
    CultureFragment people = CultureFragment.newInstance(CultureType.PEOPLE);
    CultureFragment religion = CultureFragment.newInstance(CultureType.RELIGION);
    CultureFragment social = CultureFragment.newInstance(CultureType.SOCIAL);

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return people;
            case 1:
                return religion;
            case 2:
                return social;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}