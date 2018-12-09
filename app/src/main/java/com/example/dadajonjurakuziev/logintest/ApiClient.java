package com.example.dadajonjurakuziev.logintest;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String TAG = "ApiClient";
    public static final String BASE_URL = "http://10.0.2.2:8888/loginapp/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            Log.d(TAG, "getApiClient: connected to SERVER");
        }
        return retrofit;
    }

}
