package com.example.mobilemanagementsystem.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.example.mobilemanagementsystem.Interface.UserApi;
import com.example.mobilemanagementsystem.Model.User;
import com.example.mobilemanagementsystem.R;
import com.example.mobilemanagementsystem.ServerResponse.ImageResponse;
import com.example.mobilemanagementsystem.URL.Url;
import com.example.mobilemanagementsystem.strictmode.StrictModeClass;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mobilemanagementsystem.URL.Url.token;

//import static com.example.mobilemanagementsystem.Activity.DashboardActivity.globaluser;

public class EditProfile extends AppCompatActivity {

    CircleImageView upd_userprofile;
    EditText name, email, contact, username;
    private Button updateuser;
    private String imageName = "";
    String old;
    boolean click = false;
    String imagePath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("update profile");

        upd_userprofile = findViewById(R.id.upd_userprofile);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        username=findViewById(R.id.username);
        updateuser=findViewById(R.id.updateuser);
        loadCurrentUser();
        upd_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
                click = true;
//                saveImageOnly();
            }
        });

        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

    }



    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        upd_userprofile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
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

    private void saveImageOnly() {
        if(imagePath!=null){
            File file = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                    file.getName(), requestBody);

            UserApi usersAPI = Url.getInstance().create(UserApi.class);
            Call<ImageResponse> responseBodyCall = usersAPI.uploadimage(body);

            StrictModeClass.StrictMode();
            //Synchronous methid
            try {
                Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
                imageName = imageResponseResponse.body().getFilename();
                Toast.makeText(getApplicationContext(), "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void edit(){
        saveImageOnly();
        if (!click){
            imageName=old;
        };
        User users = new User(
                name.getText().toString(),
                contact.getText().toString(),
                email.getText().toString(),
                username.getText().toString(),
                imageName

        );
        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<User> updateCall = usersAPI.updateuser(token,users);

        updateCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "succesfully updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditProfile.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCurrentUser() {

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<User> userCall = usersAPI.getuserdetails(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(EditProfile.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = Url.imagePath +  response.body().getProfileimage();


                Picasso.get().load(imgPath).into(upd_userprofile);
                imageName=response.body().getProfileimage();
                old=response.body().getProfileimage();
                name.setText(response.body().getName());
                contact.setText(response.body().getPhone());
                username.setText(response.body().getUsername());
                email.setText(response.body().getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}