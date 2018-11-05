package com.example.rus.tfproject.ui.testQuestions;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rus.tfproject.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment {

    private static final String ARGS_TITLE = "title";
    private static final String ARGS_CONTEST_URL = "contest_url";
    private static final String ARGS_STATUS = "status";

    public static TestFragment newInstance(String stringExtra, String extra, String s) {
        Log.v("tag", "TestFragment created");
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, stringExtra);
        args.putString(ARGS_CONTEST_URL, extra);
        args.putString(ARGS_STATUS, s);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        TextView titleTextView = view.findViewById(R.id.test_title);
        TextView urlTextView = view.findViewById(R.id.test_contest_url);
        TextView statusTextView = view.findViewById(R.id.test_status);
        if (getArguments() != null) {
            titleTextView.setText(getArguments().getString(ARGS_TITLE));
            urlTextView.setText(getArguments().getString(ARGS_CONTEST_URL));
            statusTextView.setText(getArguments().getString(ARGS_STATUS));
        }
    }

}
