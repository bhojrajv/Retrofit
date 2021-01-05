package com.example.retrofit.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit.Model.Responseclass;
import com.example.retrofit.Model.User;
import com.example.retrofit.R;
import com.example.retrofit.Response.RetrofitClient;
import com.example.retrofit.Response.UserResponse;

import Storage.SharedPrefmanager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class settingfrg extends Fragment implements View.OnClickListener {
private Context cotext;
Button savebtn,changebtn,loutbtn,deletbtn;
 EditText name,school,email,crrntpass,newpass;
    public settingfrg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.setting_layout,container,false);
       savebtn =view.findViewById(R.id.buttonSave);
       name   =view.findViewById(R.id.editTextName);
       school = view.findViewById(R.id.editTextSchool);
       email =view.findViewById(R.id.editTextEmail);
       crrntpass =view.findViewById(R.id.editTextCurrentPassword);
       newpass =view.findViewById(R.id.editTextNewPassword);
       changebtn=view.findViewById(R.id.buttonChangePassword);
       deletbtn=view.findViewById(R.id.buttonDelete);
       loutbtn=view.findViewById(R.id.buttonLogout);
        savebtn.setOnClickListener(this);
        changebtn.setOnClickListener(this);
        loutbtn.setOnClickListener(this);
        deletbtn.setOnClickListener(this);
       return view;
    }

    private void chnagepass() {
        String currnpass=crrntpass.getText().toString().trim();
        String newpassword=newpass.getText().toString().trim();
        String useremail=email.getText().toString().trim();

       if(TextUtils.isEmpty(currnpass))
        {
            crrntpass.setError("Password is required");
            crrntpass.requestFocus();
        }
        else if(TextUtils.isEmpty(newpassword))
        {
            newpass.setError("Password is required");
            newpass.requestFocus();
        }
        else {
            upadPassword(currnpass,newpassword,useremail);
        }
    }

    private void upadPassword(String crrntpasswrd,String newpassword,String email) {
        User user=SharedPrefmanager.getmInstance(getContext()).getuser();
        email=user.getEmail();
    Call<Responseclass> call=new RetrofitClient().
                           getmIstance().getApi()
                                .upadePass(crrntpasswrd,newpassword,email);
    call.enqueue(new Callback<Responseclass>() {
        @Override
        public void onResponse(Call<Responseclass> call, Response<Responseclass> response) {
            if(response.code()==200)
            { Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

            }
            else {

            }

        }

        @Override
        public void onFailure(Call<Responseclass> call, Throwable t) {

        }
    });
    }

    private void udateprofile() {
        String useremail=email.getText().toString().trim();
        String username=name.getText().toString().trim();
        String userschool=school.getText().toString().trim();
        if(TextUtils.isEmpty(useremail)){
            email.setError("email is required");
            email.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches())
        {
            email.setError("pleasee enter valid eamil address");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(username)){
            name.setError("name is required");
          name.requestFocus();
        }

        else if(TextUtils.isEmpty(userschool)){
            school.setError("school name  is required");
            school.requestFocus();
        }else {
            createUpadeteduser(useremail,username,userschool);
        }

    }

    private void createUpadeteduser(String email, String name, String school) {
        final User user= SharedPrefmanager.getmInstance(getContext()).getuser();
        Call<UserResponse> call=new RetrofitClient().getmIstance().getApi()
                                    .updateuser(user.getId(),email,name,school);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(!response.body().isError())
                {
                    SharedPrefmanager.getmInstance(getContext()).saveuser(response.body().getUser());
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSave:
                udateprofile();
                break;
            case R.id.buttonChangePassword:
                chnagepass();
                break;
            case R.id.buttonLogout:
                SharedPrefmanager.getmInstance(getContext()).clear();
                Intent intent=new Intent(getContext(),MainActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.buttonDelete:
                deleteuser();
        }
    }

    private void deleteuser() {
        User user=SharedPrefmanager.getmInstance(getContext()).getuser();
     Call<Responseclass> call=new RetrofitClient().getmIstance()
                               .getApi().deleteuser(user.getId());
     call.enqueue(new Callback<Responseclass>() {
         @Override
         public void onResponse(Call<Responseclass> call, Response<Responseclass> response) {
             if(!response.body().isErr()){
                 Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
             }
             else {
                 Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
             }
         }

         @Override
         public void onFailure(Call<Responseclass> call, Throwable t) {

         }
     });
    }
}
