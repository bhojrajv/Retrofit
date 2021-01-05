package com.example.retrofit.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit.Model.User;
import com.example.retrofit.R;

import Storage.SharedPrefmanager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Homefrg extends Fragment {
   TextView name,email,school;
    public Homefrg() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_homefrg, container, false);
        name=view.findViewById(R.id.nametx);
        email=view.findViewById(R.id.emailtx);
        school=view.findViewById(R.id.schooltx);
        User user= SharedPrefmanager.getmInstance(getContext()).getuser();
        name.setText(user.getName());
        email.setText(user.getEmail());
        school.setText(user.getSchool());
        // Inflate the layout for this fragment
        return view;
    }






}
