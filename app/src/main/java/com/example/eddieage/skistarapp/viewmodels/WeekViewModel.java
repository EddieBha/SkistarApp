package com.example.eddieage.skistarapp.viewmodels;

import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.eddieage.skistarapp.BR;
import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.models.Latest;
import com.example.eddieage.skistarapp.services.Services;
import com.example.eddieage.skistarapp.services.SkistarAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eddieage on 2018-02-05.
 */

public class WeekViewModel extends BaseObservable {
    private int dropHeight;
    private int liftCount;
    private int liftRides;

    @Bindable
    public String getDropHeight() {
        return ""+dropHeight;
    }

    public void setDropHeight(int dropHeight) {
        this.dropHeight = dropHeight;
        notifyPropertyChanged(BR.dropHeight);
        notifyPropertyChanged(BR.progress);
    }

    @Bindable
    public String getLiftCount() {
        return ""+liftCount;
    }

    public void setLiftCount(int liftCount) {
        this.liftCount = liftCount;
        notifyPropertyChanged(BR.liftCount);
    }

    @Bindable
    public String getLiftRides() {
        return ""+liftRides;
    }

    public void setLiftRides(int liftRides) {
        this.liftRides = liftRides;
        notifyPropertyChanged(BR.liftRides);
    }

    @Bindable
    public int getProgress()
    {
        return dropHeight/200;
    }

    public void refresh(View view)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        Services.getService().latestStatistics(sharedPreferences.getString(view.getContext().getResources().getString(R.string.skierid_nr),"")).enqueue(new Callback<Latest>()  {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                Latest latest = response.body();
                setDropHeight(latest.getLatestWeekStatistics().getDropHeight());
                setLiftCount(latest.getLatestWeekStatistics().getLiftCount());
                setLiftRides(latest.getLatestWeekStatistics().getLiftRides());

            }

            @Override
            public void onFailure(Call<Latest> call, Throwable t) {

            }
        });

    }

    private SkistarAPIService getService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.skistar.com/myskistar/game/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SkistarAPIService.class);
    }
}


