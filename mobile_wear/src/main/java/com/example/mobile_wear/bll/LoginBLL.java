package com.example.mobile_wear.bll;

import com.example.mobile_wear.Interface.UserApi;
import com.example.mobile_wear.Model.User;
import com.example.mobile_wear.ServerResponse.UserResponse;
import com.example.mobile_wear.URL.Url;

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
