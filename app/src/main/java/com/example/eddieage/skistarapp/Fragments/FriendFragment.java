package com.example.eddieage.skistarapp.Fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddieage.skistarapp.R;
import com.example.eddieage.skistarapp.databinding.FragmentFriendBinding;
import com.example.eddieage.skistarapp.viewmodels.TotalFragmentViewModel;
import com.example.eddieage.skistarapp.viewmodels.ViewModelProvider;


public class FriendFragment extends Fragment {

    private TotalFragmentViewModel viewModel;



    public FriendFragment() {
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
        viewModel = ViewModelProvider.getInstance().getBottomNavigationViewModel().totalFragmentViewModel;


        FragmentFriendBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_friend,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }





}
