package com.example.eddieage.skistarapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eddieage.skistarapp.Fragments.FriendFragment;
import com.example.eddieage.skistarapp.Fragments.SeasonFragment;
import com.example.eddieage.skistarapp.Fragments.TodayFragment;
import com.example.eddieage.skistarapp.Fragments.WeekFragment;
import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.databinding.ActivityMainBinding;
import com.example.eddieage.skistarapp.jobs.UpdateSkierStats;
import com.example.eddieage.skistarapp.viewmodels.BottomNavigationViewModel;
import com.example.eddieage.skistarapp.viewmodels.ViewModelProvider;

import java.util.Stack;


public class MainActivity extends AppCompatActivity  {

    public static final String id = "DEFAULT_CHANNEL";

    private BottomNavigationView navigation;
    private Stack<Integer> fragmentBackStack = new Stack<>();
    private Fragment friendFragment;
    private TodayFragment today;
    private Fragment week;
    private Fragment seasonFragment;
    private BottomNavigationViewModel viewModel;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()

    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    if(friendFragment == null)
                        friendFragment = new FriendFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, friendFragment, "Friends").commit();
                    fragmentBackStack.push(R.id.navigation_friends);
                    return true;
                case R.id.navigation_dagens:
                    if(today == null)
                    today = new TodayFragment();

                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, today, "Today").commit();
                    fragmentBackStack.push(R.id.navigation_dagens);
                    viewModel.setFragment(today);
                    return true;
                case R.id.navigation_week:
                    if(week == null)
                    week = new WeekFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, week, "Week").commit();
                    fragmentBackStack.push(R.id.navigation_week);
                    return true;
                case R.id.navigation_season:
                    if(seasonFragment == null)
                    seasonFragment = new SeasonFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, seasonFragment, "Season").commit();
                    fragmentBackStack.push(R.id.navigation_season);
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProvider.getInstance().getBottomNavigationViewModel();
        binding.setViewModel(viewModel);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        friendFragment = new FriendFragment();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.frameLayout, friendFragment, "Friends").commit();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_friends);

        registerNotificationChannel();
        schedulePeriodicJob();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_connection:

                Intent intent1 = new Intent(this, ConnectionActivity.class);
                startActivity(intent1);
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    private void schedulePeriodicJob() {
        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(new JobInfo.Builder(1234,
                new ComponentName(this, UpdateSkierStats.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(15 * 60000)
                .build());
    }

    private void registerNotificationChannel() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


// The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);
// The user-visible description of the channel.
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
// Configure the notification channel.
        mChannel.setDescription(description);
        notificationManager.createNotificationChannel(mChannel);
    }

    @Override
    public void onBackPressed() {
        fragmentBackStack.pop();
        if (!fragmentBackStack.empty()) {
            navigation.setSelectedItemId(fragmentBackStack.pop());
        } else
            super.onBackPressed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}
