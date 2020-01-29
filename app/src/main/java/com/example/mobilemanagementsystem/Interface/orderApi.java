package com.example.mobilemanagementsystem.Interface;

import com.example.mobilemanagementsystem.Model.order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface orderApi {
    @POST("/order")
    Call<order> orderItems(@Header("Authorization") String token, @Body order orders);
}
