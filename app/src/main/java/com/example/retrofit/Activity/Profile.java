package com.example.retrofit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retrofit.Model.User;
import com.example.retrofit.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import Storage.SharedPrefmanager;

public class Profile extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
TextView textView;
FrameLayout fragment;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       fragment=findViewById(R.id.fragmt);
        bottomNavigationView=findViewById(R.id.bottomview);
       // User user=new SharedPrefmanager(this).getuser();

       bottomNavigationView.setOnNavigationItemSelectedListener(this);
        handlefrg(new Homefrg());
    }

    private void handlefrg(Fragment fragment){
        FragmentManager manager =getSupportFragmentManager();
                   manager.beginTransaction().replace(R.id.fragmt,fragment).commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefmanager.getmInstance(this).isLogin())
        {
            Intent intent=new Intent(this,Userlogin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.home:
                Homefrg homefrg=new Homefrg();
                handlefrg(homefrg);
                break;
            case R.id.profle:
                profilefrg profilefrg=new profilefrg();
                handlefrg(profilefrg);
                break;
            case R.id.setting:
                settingfrg settingfrg=new settingfrg();
                handlefrg(settingfrg);
                break;
        }
        return true;
    }
}
