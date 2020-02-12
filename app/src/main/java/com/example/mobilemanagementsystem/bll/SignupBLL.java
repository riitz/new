package com.example.mobilemanagementsystem.bll;

import com.example.mobilemanagementsystem.Interface.UserApi;
import com.example.mobilemanagementsystem.Model.User;
import com.example.mobilemanagementsystem.ServerResponse.UserResponse;
import com.example.mobilemanagementsystem.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignupBLL {

    boolean isSuccess = false;

    public boolean signupcheck(String name, String phone, String email, String username, String password,  String profileimage) {
        User user = new User(name, phone, email, username, password, profileimage);
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> usersCall = userApi.signup(user);

        try {
            Response<UserResponse> signupresponse = usersCall.execute();
            if (signupresponse.isSuccessful()){

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
