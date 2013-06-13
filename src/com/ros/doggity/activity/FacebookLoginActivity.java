package com.ros.doggity.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ros.doggity.activity.auxiliary.MainFragment;


/**
 * Created by Ros Stepanyak
 * on 13.06.13.
 */
public class FacebookLoginActivity extends FragmentActivity {

    private MainFragment mainFragment;

    private static final String TAG = "MainFragment";



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

     if(mainFragment != null) {
         mainFragment.setContext(getApplicationContext());

     }

    }



}
