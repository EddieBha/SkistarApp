package com.example.eddieage.skistarapp.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddieage.skistarapp.databinding.LiftRideBinding;
import com.example.eddieage.skistarapp.models.LiftRide;

import java.util.List;

/**
 * Created by eddieage on 2018-02-07.
 */

class MyViewHolder extends RecyclerView.ViewHolder {
    public LiftRideBinding binding;

    public MyViewHolder(View itemView) {
        super(itemView);

        //Store binder reference for view
        binding = DataBindingUtil.bind(itemView);
    }
}

public class LiftRidesListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<LiftRide> liftRides;

    public LiftRidesListAdapter(List<LiftRide> liftRides) {
        this.liftRides = liftRides;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflate a new view and store in a new Viewholder
        View v = LiftRideBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false).getRoot();

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Assign viewModel to view
        LiftRide liftRide = liftRides.get(position);
        holder.binding.setViewModel(liftRide);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return liftRides.size();
    }

    public void setList(List<LiftRide> liftRides)
    {
        this.liftRides = liftRides;
        this.notifyDataSetChanged();
    }

}