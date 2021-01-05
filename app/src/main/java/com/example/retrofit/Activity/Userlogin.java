package com.example.retrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Model.User;
import com.example.retrofit.R;
import com.example.retrofit.Response.RetrofitClient;
import com.example.retrofit.Response.UserResponse;

import Storage.SharedPrefmanager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Userlogin extends AppCompatActivity {
 EditText logineml,lgnpass;
 Button lgnBtn;
 TextView lgnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        logineml=findViewById(R.id.emaillogin);
        lgnpass=findViewById(R.id.passlogin);
        lgnBtn=findViewById(R.id.loginBtn);
        lgnview=findViewById(R.id.regview);

        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        lgnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Userlogin.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefmanager.getmInstance(this).mInstance.isLogin())
        {
            Intent intent=new Intent(this,Profile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void loginuser() {
        String email=logineml.getText().toString();
        String pass=lgnpass.getText().toString();

        if(TextUtils.isEmpty(email)){
            logineml.setError("email is required");
            logineml.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            logineml.setError("pleasee enter valid eamil address");
            logineml.requestFocus();
        }

        else if(TextUtils.isEmpty(pass) && pass.length()<6){
            lgnpass.setError("password is required and it should be six in length");
            lgnpass.requestFocus();
        }

        loginresponse(email,pass);
    }

    private void loginresponse(String email,String password) {
       Call<UserResponse> call=new RetrofitClient()
                            .getmIstance()
                            .getApi()
                             .loginUser(email,password);
       call.enqueue(new Callback<UserResponse>() {
           @Override
           public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

               if(!response.body().isError())
               { UserResponse userResponse=response.body();

                   Toast.makeText(Userlogin.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                   SharedPrefmanager.getmInstance(Userlogin.this).saveuser(userResponse.getUser());
                   Intent intent=new Intent(Userlogin.this,Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
               }else {
                   UserResponse userResponse=response.body();
                   Toast.makeText(Userlogin.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();

               }
           }

           @Override
           public void onFailure(Call<UserResponse> call, Throwable t) {

           }
       });
    }
}
