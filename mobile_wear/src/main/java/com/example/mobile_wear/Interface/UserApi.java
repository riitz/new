package com.example.mobile_wear.Interface;

import com.example.mobile_wear.Model.User;
import com.example.mobile_wear.ServerResponse.ImageResponse;
import com.example.mobile_wear.ServerResponse.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserApi {

    @POST("users/signup")
    Call<UserResponse> signup(@Body User user);

    @POST("users/login")
    Call<UserResponse> login(@Body User user);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadimage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getuserdetails(@Header("Authorization") String token);

    @PUT("users/me")
    Call<User> updateuser(@Header("Authorization") String token, @Body User user);


}
