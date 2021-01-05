package com.example.retrofit.Response;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String Auth="Basic"+ Base64.encodeToString(("raj:raj1994").getBytes(),Base64.NO_WRAP);
    private final static String BASE_URL="http://192.168.43.193/Myproject2/public/";
    private RetrofitClient mIstance;
    private Retrofit retrofit;

    public RetrofitClient()
    {  // OkHttpClient okHttpClient=new OkHttpClient.Builder()
//                      .addInterceptor(new Interceptor() {
//                          @Override
//                          public Response intercept(Chain chain) throws IOException {
//                              Request original=chain.request();
//                            Request.Builder builder=original.newBuilder()
//                                    .addHeader("Authorization",Auth)
//                                    .method(original.method(),original.body());
//                             Request request=builder.build();
//                            return chain.proceed(request);
//                          }
//                      }).build();
          retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
  public synchronized RetrofitClient getmIstance(){
        if(mIstance==null)
        {
            mIstance=new RetrofitClient();
        }
        return mIstance;
  }
  public Api getApi(){
        return retrofit.create(Api.class);
  }
}
