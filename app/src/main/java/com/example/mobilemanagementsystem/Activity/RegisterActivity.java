package com.example.mobilemanagementsystem.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilemanagementsystem.Interface.UserApi;
import com.example.mobilemanagementsystem.Model.User;
import com.example.mobilemanagementsystem.R;
import com.example.mobilemanagementsystem.ServerResponse.ImageResponse;
import com.example.mobilemanagementsystem.ServerResponse.UserResponse;
import com.example.mobilemanagementsystem.URL.Url;
import com.example.mobilemanagementsystem.strictmode.StrictModeClass;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private CircleImageView imgProfile;
    private EditText etname, etemail, etphone, etusername, etpassword;
    private Button btnsignup;
    String imagepath;
    private String imageName = "";
    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registration");

        etname = findViewById(R.id.name);
        etemail = findViewById(R.id.email);
        etphone = findViewById(R.id.phone);
        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        btnsignup = findViewById(R.id.signup);
        imgProfile = findViewById(R.id.userprofile);
        final CircleImageView imageView;

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Browseimage();
            }


        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveimage();

                signup();
            }
        });


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {

                etname.setText("");
                etemail.setText("");
                etphone.setText("");
                etusername.setText("");
                etpassword.setText("");

//                Picasso.get().load(R.drawable.circleuser).into(imageView);
            }
        });



    }



    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void Browseimage() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagepath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveimage() {
        System.out.println("The imagePAth is:" + imagepath);
         File file = new File(imagepath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<ImageResponse> responseBodyCall = userApi.uploadimage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            //Toast.makeText(this,""+imageResponseResponse,Toast.LENGTH_SHORT).show();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted", Toast.LENGTH_SHORT).show();

            //Toast.makeText(getApplicationContext(),"response:"+responseBodyCall,Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private void signup() {


        String fname = etname.getText().toString();
        String phone = etphone.getText().toString();
        String email = etemail.getText().toString();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        User users = new User(fname,email,phone, username, password, imageName);

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<UserResponse> signUpCall = usersAPI.signup(users);

        signUpCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                Url.token += response.body().getToken();
                System.out.println(Url.token);
//                openDashboard();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}














