package com.example.mobilemanagementsystem.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemanagementsystem.Interface.UserApi;
import com.example.mobilemanagementsystem.Model.User;
import com.example.mobilemanagementsystem.R;
import com.example.mobilemanagementsystem.ServerResponse.UserResponse;
import com.example.mobilemanagementsystem.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public SensorManager sensorMnager;


    private EditText etusername,etpassword;
    private Button btnlogin;
    private TextView txtreg;
    private CheckBox chk;
    private SensorManager sensorManager;
    Vibrator vibrator;

    SharedPreferences sharedPreferences,token;

    String name,Username,Password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide();

        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        btnlogin = findViewById(R.id.login);
        chk = findViewById(R.id.chkrememberme);







        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        txtreg = findViewById(R.id.tvSignUp);

        txtreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });




//        lightsensor();


    }
    public void login()
    {
        String uname = etusername.getText().toString();
        String pwd = etpassword.getText().toString();


        User user = new User(uname,pwd);

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> userloginresponse = userApi.login(user);

        userloginresponse.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Username or password not match",Toast.LENGTH_SHORT).show();
                    Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibrator.vibrate(2000);
                }else{
                    sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",etusername.getText().toString());
                    editor.putString("token", response.body().getToken());

                    Url.token += response.body().getToken();



                    openDashBoard();







                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    public void openDashBoard(){
        Intent i = new Intent(LoginActivity.this,productActivity.class);
        startActivity(i);
    }

//
//    private void lightsensor() {
//        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
//        Sensor sensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        SensorEventListener sensorEventListener= new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent event) {
//                if( event.sensor.getType() == Sensor.TYPE_LIGHT) {
//                    Toast.makeText(getApplicationContext(), "On SensorChanged" + event.values[0], Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//            }
//        };
//        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
//
//    }
//



}