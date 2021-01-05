package com.example.retrofit.Activity;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit.Model.User;
import com.example.retrofit.Model.UsersResponse;
import com.example.retrofit.R;
import com.example.retrofit.Response.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class profilefrg extends Fragment {

 RecyclerView recyclerView;
 private Context context;
 private RecAddapter addapter;
 private List<User>userlist;
    public profilefrg() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank,container,false);
        recyclerView=view.findViewById(R.id.recview);
        userlist=new ArrayList<>();
        Call<UsersResponse> call= new RetrofitClient()
                              .getmIstance().
                              getApi().getusers();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                userlist=response.body().getUsers();

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                addapter=new RecAddapter(getActivity(),userlist);
                recyclerView.setAdapter(addapter);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
