package com.example.rus.tfproject.ui.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.rus.tfproject.R;
import com.example.rus.tfproject.ui.mainActivity.fragments.myCourse.MyCourseFragment;
import com.example.rus.tfproject.ui.mainActivity.fragments.profile.ProfileFragment;
import com.example.rus.tfproject.ui.mainActivity.fragments.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT_MY_COURSE = "fragment_my_course";
    private static final String TAG_FRAGMENT_PROFILE = "fragment_profile";
    private static final String TAG_FRAGMENT_SETTINGS = "fragment_settings";

    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Fragment currentFragment;

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

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initViews() {

        fragmentManager = getSupportFragmentManager();

        findViews();
        setupBottomNavigationView();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
 /*       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_my_course:
                        fragment = MyCourseFragment.newInstance();
                        break;
                    case R.id.action_profile:
                        fragment = ProfileFragment.newInstance();
                        break;
                    case R.id.action_settings:
                        fragment = SettingsFragment.newInstance();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, fragment).commit();
                return true;
            }
        });*/

        // not working as intended but works with addToBackStack
       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.action_my_course:
                       loadFragmentByTag(TAG_FRAGMENT_MY_COURSE);
                       break;
                   case R.id.action_profile:
                       loadFragmentByTag(TAG_FRAGMENT_PROFILE);
                       break;
                   case R.id.action_settings:
                       loadFragmentByTag(TAG_FRAGMENT_SETTINGS);
                       break;
               }
               return true;
           }
       });
    }

    private void loadFragmentByTag(String tagFragment) {
        Fragment fragment = fragmentManager.findFragmentByTag(tagFragment);
        if (fragment==null){
            Log.v("TAG", "no tag");
            switch (tagFragment){
                case TAG_FRAGMENT_MY_COURSE:
                    fragment = MyCourseFragment.newInstance();
                    break;
                case TAG_FRAGMENT_PROFILE:
                    fragment = ProfileFragment.newInstance();
                    break;
                case TAG_FRAGMENT_SETTINGS:
                    fragment = SettingsFragment.newInstance();
                    break;
            }
        } else {
            Log.v("TAG", "Tag found");
        }
        if (currentFragment == null){
            fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, fragment, tagFragment)
                    .addToBackStack(null).commit();
            currentFragment = fragment;
        } else {
            if (!currentFragment.equals(fragment)){
                fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, fragment, tagFragment)
                        .addToBackStack(null).commit();
                currentFragment = fragment;
            }
        }
    }

    private void setupBottomNavigationView() {
        bottomNavigationView.setSelectedItemId(R.id.action_profile);
        //getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, ProfileFragment.newInstance()).commit();

        loadFragmentByTag(TAG_FRAGMENT_PROFILE);
    }

    private void findViews() {
        bottomNavigationView = findViewById(R.id.main_activity_bottom_navigation_view);
    }
}
