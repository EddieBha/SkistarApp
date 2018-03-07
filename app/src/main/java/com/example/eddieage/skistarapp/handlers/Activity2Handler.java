package com.example.eddieage.skistarapp.handlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.eddieage.skistarapp.Activities.MainActivity;


public class Activity2Handler {

    private Context context;

    public Activity2Handler(Context context)
    {
        this.context = context;
    }

    public void nextButton(View view)
    {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


}
