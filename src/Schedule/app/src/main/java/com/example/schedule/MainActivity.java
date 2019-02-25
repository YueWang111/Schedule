package com.example.schedule;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.schedule.tab.TabHost;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {
    private TabHost tabHost;
    private FragmentTabHost fragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //My app dont need title(Hidding title)
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        //Using fragmentTabHost
        tabHost = new TabHost(getApplicationContext());
        //Instantiate tabhost
        //ScheduleShow - PlanList  (MAIN_VIEW)
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.onCreate(fragmentTabHost, getSupportFragmentManager());


        //SQL Create
        LitePal.getDatabase();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
