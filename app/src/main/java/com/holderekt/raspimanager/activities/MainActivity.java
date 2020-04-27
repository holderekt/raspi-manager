package com.holderekt.raspimanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.os.StrictMode;

import com.holderekt.raspimanager.R;
import com.holderekt.raspimanager.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBarSettings();

       fragmentLoader(new MainFragment());
    }

    @SuppressLint("WrongConstant")
    public void actionBarSettings(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
    }

    public void fragmentLoader(Fragment frag){
        ft.add(R.id.flContainer, frag);
        ft.commit();
    }


}
