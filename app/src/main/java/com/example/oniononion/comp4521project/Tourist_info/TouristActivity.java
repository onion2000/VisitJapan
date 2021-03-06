package com.example.oniononion.comp4521project.Tourist_info;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.R;
import com.example.oniononion.comp4521project.ToolbarInstaller;

/**
 * Created by ylcheung on 22/5/16.
 */
public class TouristActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition mEnterTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.enter_transition);
        Transition mReturnTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.return_transition);

        getWindow().setEnterTransition(mEnterTran);
        getWindow().setReturnTransition(mReturnTran);
        setContentView(R.layout.tourist_activity);

        NavigationDrawerInstaller.installOnActivity(this);
        ToolbarInstaller.installOnActivity(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tourist_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Restaurants"));
        tabLayout.addTab(tabLayout.newTab().setText("Hotels / Ryokan"));
        tabLayout.addTab(tabLayout.newTab().setText("Sightseeing Spot"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tourist_pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
