package com.example.mobile_wear.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {



//   for PC use
 public static final String BASE_URL ="http://10.0.2.2:3002/";

    //  for mobile use
//    public static final String  BASE_URL="http://192.168.137.105:3002/";


    public static String token = "Bearer ";
    public static String imagePath = BASE_URL + "uploads/";


    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
