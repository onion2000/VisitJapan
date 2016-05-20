package com.example.oniononion.comp4521project;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.oniononion.comp4521project.Culture_info.CultureMenuActivity;
import com.example.oniononion.comp4521project.Currency_converter.ConverterActivity;
import com.example.oniononion.comp4521project.Japanese_vocab.VocabActivity;
import com.example.oniononion.comp4521project.Translation.TranslationActivity;
import com.example.oniononion.comp4521project.Travel_information.TravelActivity;
import com.example.oniononion.comp4521project.Weather_forecast.OnedayWeatherActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


/**
 * Created by oniononion on 12/3/2016.
 */
public class NavigationDrawerInstaller {
        public static Drawer installOnActivity(final Activity act) {


        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Function Menu").withTextColorRes(R.color.material_drawer_primary_dark);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("Translation").withIcon(GoogleMaterial.Icon.gmd_translate);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName("Weather Forecast").withIcon(FontAwesome.Icon.faw_sun_o);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName("Currency Converter").withIcon(FontAwesome.Icon.faw_jpy);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withName("Local Culture").withIcon(GoogleMaterial.Icon.gmd_local_cafe);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withName("Tourist Information").withIcon(FontAwesome.Icon.faw_hospital_o);
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withName("Japanese Vocabulary").withIcon(FontAwesome.Icon.faw_font);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withName("Travel Information").withIcon(FontAwesome.Icon.faw_subway);

        Drawer result = new DrawerBuilder()
                .withActivity(act)
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
                        if(CheckConnectivityNotification.isOnline(act.getApplicationContext())) {
                            switch (position) {
                                case 2:
                                    intent = new Intent(act, TranslationActivity.class);
                                    act.startActivity(intent);
                                    break;
                                case 3:
                                    intent = new Intent(act, OnedayWeatherActivity.class);
                                    act.startActivity(intent);
                                    break;
                                case 4:
                                    intent = new Intent(act, ConverterActivity.class);
                                    act.startActivity(intent);
                                    break;
                                case 5:
                                    intent = new Intent(act, CultureMenuActivity.class);
                                    act.startActivity(intent);
                                    break;
                                case 6:
                                    break;
                                case 7:
                                    intent = new Intent(act, VocabActivity.class);
                                    act.startActivity(intent);
                                    break;
                                case 8:
                                    intent = new Intent(act, TravelActivity.class);
                                    act.startActivity(intent);
                                    break;

                                default:
                                    break;

                            }
                        }else{
                            CheckConnectivityNotification.checkOnline(act);
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
