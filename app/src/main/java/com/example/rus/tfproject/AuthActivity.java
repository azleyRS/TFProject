package com.example.rus.tfproject;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rus.tfproject.network.AuthEndpoint;
import com.example.rus.tfproject.network.DTO.User;
import com.example.rus.tfproject.network.TFApi;
import com.example.rus.tfproject.ui.mainActivity.MainActivity;

import java.util.HashSet;

public class AuthActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIfLogin();

        init();
    }

    private void checkIfLogin() {
        //change this later using    api/user
        if(!PreferenceManager.getDefaultSharedPreferences(this).getStringSet("cookies", new HashSet<String>()).isEmpty()){
            Intent intent = MainActivity.newIntent(this);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

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
        final Context context = this;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        AuthEndpoint authEndpoint = TFApi.getInstance().getAuthEndpoint();
        authEndpoint.authenticate(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        HashSet<String> set = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet("cookies", new HashSet<String>());
                        Log.v("TEST", set.toString());
                        checkIfLogin();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void findViews() {
        loginButton = findViewById(R.id.auth_login_button);
        emailEditText = findViewById(R.id.auth_email_editText);
        passwordEditText = findViewById(R.id.auth_password_editText);
    }
}
