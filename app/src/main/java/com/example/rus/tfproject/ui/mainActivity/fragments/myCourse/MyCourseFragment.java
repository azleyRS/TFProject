package com.example.rus.tfproject.ui.mainActivity.fragments.myCourse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rus.tfproject.R;

public class MyCourseFragment extends Fragment {

    public static MyCourseFragment newInstance() {
        Log.v("tag", "MyCourse created");
        return new MyCourseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_course, container, false);
    }
}