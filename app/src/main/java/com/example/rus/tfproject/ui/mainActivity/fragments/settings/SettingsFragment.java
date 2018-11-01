package com.example.rus.tfproject.ui.mainActivity.fragments.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rus.tfproject.R;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance() {
        Log.v("tag", "Settings created");
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

}
