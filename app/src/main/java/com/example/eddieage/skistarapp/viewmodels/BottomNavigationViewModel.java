package com.example.eddieage.skistarapp.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;

import com.example.eddieage.skistarapp.BR;
import com.example.eddieage.skistarapp.Fragments.TodayFragment;


public class BottomNavigationViewModel extends BaseObservable {

    private boolean loading;

    public void setFragment(TodayFragment fragment) {
        this.fragment = fragment;
    }

    private TodayFragment fragment;


    public void onRefresh(Context context) {

        if (fragment != null)
            fragment.refresh(context);

        new Handler().postDelayed(() -> setLoading(false), 2000);
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public TodayViewModel todayViewModel = new TodayViewModel();


    public WeekViewModel weekViewModel = new WeekViewModel();

    public TotalFragmentViewModel totalFragmentViewModel = new TotalFragmentViewModel();


}