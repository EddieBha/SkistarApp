package com.example.eddieage.skistarapp.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.databinding.FragmentTodayBinding;
import com.example.eddieage.skistarapp.viewmodels.TodayViewModel;
import com.example.eddieage.skistarapp.viewmodels.ViewModelProvider;



public class TodayFragment extends Fragment {


    private TodayViewModel viewModel;

    public TodayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProvider.getInstance().getBottomNavigationViewModel().todayViewModel;

        FragmentTodayBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    public void refresh(Context context) {

        viewModel.refresh(context);
    }


}