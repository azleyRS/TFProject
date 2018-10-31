package com.example.rus.tfproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.rus.tfproject.AuthActivity;
import com.example.rus.tfproject.R;

public class MainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        //not in one row in case i need to put something in intent
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
