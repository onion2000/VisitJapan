package com.example.oniononion.comp4521project.Japanese_vocab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by oniononion on 2016/04/20.
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
                return  new VocabMainFragment();
            case 1:
                return new PhrasesFragment();
            case 2:
                return new PhrasesFragment2();
            case 3:
                return new WordsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}