package com.example.rus.tfproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViews();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });
    }

    private void onLoginClick() {

    }

    private void findViews() {
        loginButton = findViewById(R.id.auth_login_button);
        emailEditText = findViewById(R.id.auth_email_editText);
        passwordEditText = findViewById(R.id.auth_password_editText);
    }
}
