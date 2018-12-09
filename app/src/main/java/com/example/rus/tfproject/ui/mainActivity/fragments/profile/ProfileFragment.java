package com.example.rus.tfproject.ui.mainActivity.fragments.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rus.tfproject.R;
import com.example.rus.tfproject.network.DTO.User;
import com.example.rus.tfproject.network.DTO.UserOuterResponce;
import com.example.rus.tfproject.network.TFApi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileFragment extends Fragment {

    ImageView avatarImageView;
    TextView dobTextView, emailTextView, firstNameTextView, lastNameTextView, thirdNameTextView, regionTextView, universityTextView;
    Button changeButton;

    public static ProfileFragment newInstance() {
        Log.v("tag", "Profile created");
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        avatarImageView = view.findViewById(R.id.avatar_image_view);
        dobTextView = view.findViewById(R.id.dob_text_view);
        emailTextView = view.findViewById(R.id.email_text_view);
        firstNameTextView = view.findViewById(R.id.first_name_text_view);
        lastNameTextView = view.findViewById(R.id.last_name_text_view);
        thirdNameTextView = view.findViewById(R.id.third_text_view);
        regionTextView = view.findViewById(R.id.region_text_view);
        universityTextView = view.findViewById(R.id.university_text_view);
        changeButton = view.findViewById(R.id.change_button);

        loadUserInfo();

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void loadUserInfo() {
        TFApi.getInstance().getAuthEndpoint().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserOuterResponce>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UserOuterResponce userOuterResponce) {
                        fillViews(userOuterResponce.user);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void fillViews(User user) {
        if (user.avatar!=null){
            Glide.with(getContext()).load("https://fintech.tinkoff.ru" + user.avatar).into(avatarImageView);
            avatarImageView.setVisibility(View.VISIBLE);
        }
        if (user.birthday!=null){
            dobTextView.setText(user.birthday);
            dobTextView.setVisibility(View.VISIBLE);
        }
        if (user.email!=null){
            emailTextView.setText(user.email);
            emailTextView.setVisibility(View.VISIBLE);
        }
        if (user.firstName!=null){
            firstNameTextView.setText(user.firstName);
            firstNameTextView.setVisibility(View.VISIBLE);
        }
        if (user.lastName!=null){
            lastNameTextView.setText(user.lastName);
            lastNameTextView.setVisibility(View.VISIBLE);
        }
        if (user.middleName!=null){
            thirdNameTextView.setText(user.middleName);
            thirdNameTextView.setVisibility(View.VISIBLE);
        }
        if (user.region!=null){
            regionTextView.setText(user.region);
            regionTextView.setVisibility(View.VISIBLE);
        }
        if (user.university!=null){
            universityTextView.setText(user.university);
            universityTextView.setVisibility(View.VISIBLE);
        }

    }
}
