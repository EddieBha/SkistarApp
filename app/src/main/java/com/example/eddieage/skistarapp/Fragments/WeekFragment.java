package com.example.eddieage.skistarapp.Fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.databinding.FragmentWeekBinding;
import com.example.eddieage.skistarapp.viewmodels.ViewModelProvider;
import com.example.eddieage.skistarapp.viewmodels.WeekViewModel;


public class WeekFragment extends Fragment {


private WeekViewModel viewModel;

    public WeekFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider.getInstance().getBottomNavigationViewModel().weekViewModel;

        FragmentWeekBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }


}
