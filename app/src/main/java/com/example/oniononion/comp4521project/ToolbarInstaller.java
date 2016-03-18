package com.example.oniononion.comp4521project;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.oniononion.comp4521project.R;
import com.mikepenz.materialdrawer.Drawer;

/**
 * Created by oniononion on 18/3/2016.
 */
public class ToolbarInstaller {

        public static Toolbar installOnActivity(final AppCompatActivity act) {
            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) act.findViewById(R.id.toolbar);
            act.setSupportActionBar(toolbar);


            if(!act.getClass().getSimpleName().equals("MainActivity")) {
                act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                act.getSupportActionBar().setDisplayShowHomeEnabled(true);
                toolbar.setNavigationIcon(act.getResources().getDrawable(R.drawable.ic_action_back));
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        act.onBackPressed();
                    }
                });

            }
            return toolbar;
        }


}
