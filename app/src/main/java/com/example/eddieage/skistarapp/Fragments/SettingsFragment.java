package com.example.eddieage.skistarapp.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.eddieage.skistarapp.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}