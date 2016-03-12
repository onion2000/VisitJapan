package com.example.oniononion.comp4521project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.oniononion.comp4521project.Currency_converter.ConverterActivity;
import com.example.oniononion.comp4521project.Weather_forecast.WeatherActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
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


    public static Drawer installOnActivity(Activity act) {
        final Activity activity= act;
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Function Menu").withTextColorRes(R.color.material_drawer_primary_dark);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("Translation").withIcon(GoogleMaterial.Icon.gmd_translate);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName("Weather Forecast").withIcon(FontAwesome.Icon.faw_sun_o);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName("Currency Converter").withIcon(FontAwesome.Icon.faw_jpy);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withName("Local Culture").withIcon(GoogleMaterial.Icon.gmd_local_cafe);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withName("Tourist Information").withIcon(FontAwesome.Icon.faw_hospital_o);
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withName("Japanese Vocabulary").withIcon(FontAwesome.Icon.faw_font);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withName("Travel Information").withIcon(FontAwesome.Icon.faw_subway);


        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withTranslucentNavigationBar(false)
                .withActionBarDrawerToggle(false)
                .withDrawerLayout(R.layout.material_drawer)
                .withDrawerWidthDp(280)
                .addDrawerItems(
                        item1.withSelectable(false),
                        new DividerDrawerItem(),
                        item2, item3, item4, item5, item6, item7, item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent;
                        switch (position) {
                            case 1: break;
                            case 2:

                                break;
                            case 3:
                                intent = new Intent(activity, WeatherActivity.class);
                                activity.startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(activity, ConverterActivity.class);
                                activity.startActivity(intent);
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                 break;

                            default:
                                break;

                        }
                        return true;
                    }


                })
                .withShowDrawerOnFirstLaunch(true)
                .build();


        result.setSelection(-1);
        return result;

    }

}
