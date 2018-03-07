package com.example.eddieage.skistarapp.Activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.eddieage.skistarapp.Fragments.SettingsFragment;
import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.jobs.UpdateSkierStats;

/**
 * Created by eddieage on 2018-02-07.
 */

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences.OnSharedPreferenceChangeListener listener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .commit();
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listener =   new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

                JobScheduler jobScheduler =
                        (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

                if( sharedPreferences.getBoolean("auto_uppdatering",false))
                {
                    jobScheduler.schedule(new JobInfo.Builder(1234,
                            new ComponentName(SettingsActivity.this, UpdateSkierStats.class))
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .setPeriodic(Integer.parseInt(sharedPreferences.getString("update_frequency_preference","15")) * 60000)
                            .build());
                }
                else
                {
                    // Call cancel to stop scheduling of job
                    jobScheduler.cancel(1234);
                }
            }
        };

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(listener);

    }
}