package com.example.eddieage.skistarapp.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.preference.PreferenceManager;

import com.example.eddieage.skistarapp.BR;
import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.models.Latest;
import com.example.eddieage.skistarapp.services.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayViewModel extends BaseObservable {

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

    public void refresh(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Services.getService().latestStatistics(sharedPreferences.getString(context.getResources().getString(R.string.skierid_nr),"")).enqueue(new Callback<Latest>() {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                Latest latest = response.body();
                setDropHeight(latest.getLatestDayStatistics().getDropHeight());
                setLiftCount(latest.getLatestDayStatistics().getLiftCount());
                setLiftRides(latest.getLatestDayStatistics().getLiftRides());
            }

            @Override
            public void onFailure(Call<Latest> call, Throwable t) {

            }
        });

    }

   /* private SkistarAPIService getService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.skistar.com/myskistar/game/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SkistarAPIService.class);
    }*/
}
