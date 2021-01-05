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

import com.example.retrofit.R;
import com.example.retrofit.Model.Responseclass;
import com.example.retrofit.Response.RetrofitClient;
import com.example.retrofit.Response.UserResponse;

import Storage.SharedPrefmanager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
 EditText editname,editemail,editpassword,editschool;
 Button signupbtn;
 TextView logintxt;

 //String email="",name="",password="",school="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editemail=findViewById(R.id.email);
        editname=findViewById(R.id.name);
        editpassword=findViewById(R.id.pass);
        editschool=findViewById(R.id.school);
        signupbtn=findViewById(R.id.signupBtn);
        logintxt=findViewById(R.id.loginview);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signuser();
            }
        });

  logintxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent=new Intent(MainActivity.this,Userlogin.class);
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

    private void signuser() {
       String email=editemail.getText().toString().trim();
        String name=editname.getText().toString().trim();
        String password=editpassword.getText().toString().trim();
        String school=editschool.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            editemail.setError("email is required");
            editemail.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editemail.setError("pleasee enter valid eamil address");
            editemail.requestFocus();
        }
       else if(TextUtils.isEmpty(name)){
            editemail.setError("name is required");
            editemail.requestFocus();
        }
        else if(TextUtils.isEmpty(password) && password.length()<6){
            editemail.setError("password is required and it should be six in length");
            editemail.requestFocus();
        }
       else if(TextUtils.isEmpty(school)){
            editemail.setError("school name  is required");
            editemail.requestFocus();
        }else {
            createUserSignUp(email,password,name,school);
        }


    }

    private void createUserSignUp(String email,String password,String name,String school) {


       Call<Responseclass>call=new RetrofitClient()
               .getmIstance().
                       getApi().
                       signUp(email,password,name,school);
        call.enqueue(new Callback<Responseclass>() {
            @Override
            public void onResponse(Call<Responseclass> call, Response<Responseclass> response) {
               // String s = null;

                if(response.code()==201)
                {    Responseclass responseclass =response.body();
                    Toast.makeText(MainActivity.this, responseclass.getMsg(), Toast.LENGTH_SHORT).show();
                }
                if(response.code()==422)
                {
                    Responseclass responseclass=response.body();
                    Toast.makeText(MainActivity.this, "user Alredy exists", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Responseclass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error:"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
