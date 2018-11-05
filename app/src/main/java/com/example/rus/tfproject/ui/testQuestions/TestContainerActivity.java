package com.example.rus.tfproject.ui.testQuestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.rus.tfproject.R;
import com.example.rus.tfproject.network.DTO.DetailedTask;

public class TestContainerActivity extends AppCompatActivity {
    private static final String TITLE_EXTRA = "title";
    private static final String CONTEST_URL_EXTRA = "contest_url";
    private static final String STATUS_EXTRA = "status";
    FragmentManager fragmentManager;

    public static Intent newIntent(Context context, DetailedTask tast) {
        Intent intent = new Intent(context, TestContainerActivity.class);
        intent.putExtra(TITLE_EXTRA, tast.title);
        intent.putExtra(CONTEST_URL_EXTRA, tast.contestInfo.contestUrl);
        intent.putExtra(STATUS_EXTRA, tast.contestInfo.contestStatus.status);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_container);
        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();

        TestFragment fragment = TestFragment.newInstance(getIntent().getStringExtra(TITLE_EXTRA),
                getIntent().getStringExtra(CONTEST_URL_EXTRA),
                getIntent().getStringExtra(STATUS_EXTRA));

        //add tag later
        fragmentManager.beginTransaction().replace(R.id.test_fragment_container, fragment).commit();
    }
}
