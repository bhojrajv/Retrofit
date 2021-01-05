package com.example.retrofit.Response;

import com.example.retrofit.Model.Responseclass;
import com.example.retrofit.Model.UsersResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
  @FormUrlEncoded
    @POST("createuser")
    Call<Responseclass>signUp(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school

 );
  @FormUrlEncoded
    @POST("loginUser")
    Call<UserResponse>loginUser(
             @Field("email") String email,
             @Field("password") String password
  );
  @GET("allusers")
    Call<UsersResponse>getusers();

  @FormUrlEncoded
    @POST("update/{id}")
    Call<UserResponse>updateuser(
                @Path("id") int id,
                @Field("email") String email,
                @Field("name") String name,
                @Field("school") String school
  );
  @FormUrlEncoded
    @POST("updatepass")
    Call<Responseclass>upadePass(
             @Field("currentPassword") String currentPassword,
             @Field("newPassword") String newPassword,
             @Field("email") String email
  );

    @DELETE("delete/{id}")
    Call<Responseclass>deleteuser(
             @Path("id") int id
  );
}
