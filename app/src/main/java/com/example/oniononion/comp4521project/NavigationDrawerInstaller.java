package com.example.oniononion.comp4521project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.example.oniononion.comp4521project.Weather_forecast.WeatherActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by oniononion on 12/3/2016.
 */
public class NavigationDrawerInstaller {


    public static void installOnActivity(final Activity act) {
        final Activity activity= act;
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Function");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("Translation");
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName("Weather Forecast");
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName("Currency Converter");
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withName("Local Culture");
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withName("Tourist Information");
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withName("Japanese Vocabulary");
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withName("Travel Information");



        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withTranslucentNavigationBar(false)
                .withActionBarDrawerToggle(false)
                .withDrawerLayout(R.layout.material_drawer)
                .withDrawerWidthDp(300)
                .addDrawerItems(
                        item1.withSelectable(false),
                        new DividerDrawerItem(),
                        item2, item3, item4, item5, item6, item7, item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:
                                Intent intent = new Intent(activity, WeatherActivity.class);
                                act.startActivity(intent);
                                break;
                            case 4:

                                break;
                            default:
                                break;

                        }
                        return true;
                    }
                })
                .build();


        result.setSelection(-1);
    }



}
