package com.example.eddieage.skistarapp.Fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.adapters.LiftRidesListAdapter;
import com.example.eddieage.skistarapp.databinding.FragmentSeasonBinding;
import com.example.eddieage.skistarapp.models.LiftRide;
import com.example.eddieage.skistarapp.services.Services;
import com.example.eddieage.skistarapp.viewmodels.TotalFragmentViewModel;
import com.example.eddieage.skistarapp.viewmodels.ViewModelProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeasonFragment extends Fragment {

    private TotalFragmentViewModel viewModel;

    public SeasonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider.getInstance().getBottomNavigationViewModel().totalFragmentViewModel;

        FragmentSeasonBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_season,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_total, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Services.getService().liftRides("3206","13").enqueue(new Callback<List<LiftRide>>() {
            @Override
            public void onResponse(Call<List<LiftRide>> call, Response<List<LiftRide>> response) {
                RecyclerView list = getActivity().findViewById(R.id.recyclerView);
                list.setAdapter(new LiftRidesListAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<LiftRide>> call, Throwable t) {

            }
        });
    }




}
