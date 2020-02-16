package com.example.mobile_wear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_wear.Interface.UserApi;
import com.example.mobile_wear.Model.User;
import com.example.mobile_wear.ServerResponse.UserResponse;
import com.example.mobile_wear.URL.Url;
import com.google.android.gms.maps.model.Dash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends WearableActivity {


    public SensorManager sensorMnager;


    private EditText etusername, etpassword;
    private Button btnlogin;
    private TextView txtreg;

    SharedPreferences sharedPreferences, token;

    String name, Username, Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide();

        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        btnlogin = findViewById(R.id.login);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(LoginActivity.this, DashActivity.class);
                startActivity(intent3);
            }
        });


//        lightsensor();


    }

    public void login() {
        String uname = etusername.getText().toString();
        String pwd = etpassword.getText().toString();


        User user = new User(uname, pwd);

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> userloginresponse = userApi.login(user);

        userloginresponse.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", etusername.getText().toString());
                editor.putString("token", response.body().getToken());

                Url.token += response.body().getToken();


                openDashBoard();


            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void openDashBoard() {
        Intent i = new Intent(LoginActivity.this, DashActivity.class);
        startActivity(i);
    }




}