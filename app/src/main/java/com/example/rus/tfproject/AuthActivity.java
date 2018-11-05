package com.example.rus.tfproject;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rus.tfproject.network.AuthEndpoint;
import com.example.rus.tfproject.network.DTO.ErrorResponse;
import com.example.rus.tfproject.network.DTO.User;
import com.example.rus.tfproject.network.DTO.WrapperResponce;
import com.example.rus.tfproject.network.TFApi;
import com.example.rus.tfproject.ui.mainActivity.MainActivity;

import java.io.IOException;
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

        testing();
    }

    private void testing() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
        HashSet<String> cookiesSet = (HashSet<String>) sharedPreferences.getStringSet("cookies", new HashSet<String>());
        Log.v("AuthSP", cookiesSet.toString());
    }

    private void checkIfLogin() {
        if (MyApplication.isCookies()){
            Log.v("AuthCheckIfLogin", "MyApplication.isCookies() = true");
            TFApi.getInstance().getAuthEndpoint().checkAuthResponceBody()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            try {
                                String res = responseBody.string();
                                if (res.contains("User not authenticated")){
                                    Log.v("AuthRes", "Error");
                                    MyApplication.setCookies(false);
                                } else if (res.contains("first")){
                                    Log.v("AuthRes", "User");

                                    goToMainActivity();
                                }
                                Log.v("AuthRes", res);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
                            HashSet<String> cookiesSet = (HashSet<String>) sharedPreferences.getStringSet("cookies", new HashSet<String>());
                            Log.v("AuthResCookies", cookiesSet.toString());

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        } else {
            Log.v("AuthCheckIfLogin", "MyApplication.isCookies() = false");
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
        MyApplication.setCookies(false);
        final Context context = this;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        /*AuthEndpoint authEndpoint = TFApi.getInstance().getAuthEndpoint();
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
                        Log.v("TAG", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/



        Disposable disposable = TFApi.getInstance().getAuthEndpoint().authenticateSingle(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> {
                    if (userResponse.isSuccessful()){
                        Log.v("Auth", String.valueOf(userResponse.code()));
                        Log.v("Auth", userResponse.message());
                        Log.v("Auth", String.valueOf(userResponse.headers()));
                        Log.v("Auth", String.valueOf(userResponse.body()));

                        //goToMainActivity();

                        //testing
                        MyApplication.setCookies(true);



                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
                        HashSet<String> cookiesSet = (HashSet<String>) sharedPreferences.getStringSet("cookies", new HashSet<String>());
                        Log.v("AuthResCookies", cookiesSet.toString());




                        checkIfLogin();
                    } else if (userResponse.code()==403){
                        showWrongDataTyped();
                    }
                }, throwable -> {
                    Log.v("Auth", throwable.getMessage());
                    showNoInternetConnection();
                });

    }

    private void showNoInternetConnection() {
        //someCodeHere
    }

    private void goToMainActivity() {
        Intent intent = MainActivity.newIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showWrongDataTyped() {
        Toast.makeText(this, "Incorrect data entered, plz try again", Toast.LENGTH_SHORT).show();
    }

    private void findViews() {
        loginButton = findViewById(R.id.auth_login_button);
        emailEditText = findViewById(R.id.auth_email_editText);
        passwordEditText = findViewById(R.id.auth_password_editText);
    }
}
