package com.example.mobilemanagementsystem.bll;

import com.example.mobilemanagementsystem.Interface.UserApi;
import com.example.mobilemanagementsystem.Model.User;
import com.example.mobilemanagementsystem.ServerResponse.UserResponse;
import com.example.mobilemanagementsystem.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;

    public boolean checkLogin(String username, String password) {
        User user = new User(username, password);
        UserApi rideshareApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> usersCall = rideshareApi.login(user);

        try {
            Response<UserResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful()){

             //   url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
