package com.example.rus.tfproject.ui.mainActivity.fragments.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rus.tfproject.R;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        Log.v("tag", "Profile created");
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
