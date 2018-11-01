package com.example.rus.tfproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.rus.tfproject.AuthActivity;
import com.example.rus.tfproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    public static Intent newIntent(Context context) {
        //not in one row in case i need to put something in intent
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
    }

    private void initViews() {
        findViews();
        bottomNavigationView.setSelectedItemId(R.id.action_profile);
    }

    private void findViews() {
        bottomNavigationView = findViewById(R.id.main_activity_bottom_navigation_view);
    }
}
