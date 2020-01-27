package com.example.mobilemanagementsystem.Interface;

import com.example.mobilemanagementsystem.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface productApi {

    @GET("product/")
    Call<List<Product>> getProductDetails();
}
