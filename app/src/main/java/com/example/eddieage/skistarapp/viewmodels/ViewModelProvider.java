package com.example.eddieage.skistarapp.viewmodels;

/**
 * Created by eddieage on 2018-02-06.
 */

public class ViewModelProvider {
    private static final ViewModelProvider ourInstance = new ViewModelProvider();

    public static ViewModelProvider getInstance() {
        return ourInstance;
    }

    private ViewModelProvider() {
        bottomNavigationViewModel = new BottomNavigationViewModel();
    }

    private BottomNavigationViewModel bottomNavigationViewModel;

    public BottomNavigationViewModel getBottomNavigationViewModel()
    {
        return  bottomNavigationViewModel;
    }

}
