package com.example.mobilemanagementsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemanagementsystem.Adapater.prodAdapater;
import com.example.mobilemanagementsystem.Interface.productApi;
import com.example.mobilemanagementsystem.Model.Product;
import com.example.mobilemanagementsystem.R;
import com.example.mobilemanagementsystem.URL.Url;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class productActivity extends AppCompatActivity {
    private LinearLayoutManager layoutManager;
    RecyclerView rvProd;
    BottomNavigationView bottomNavigation;

    public static List<Product> foodlist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        rvProd = findViewById(R.id.rvProd);

        try {
            productApi prodApi = Url.getInstance().create(productApi.class);
            Call<List<Product>> productCall = prodApi.getProductDetails();

            productCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(productActivity.this, "Couldn't get products", Toast.LENGTH_SHORT).show();
                    }
                    List<Product> productList = response.body();
                    layoutManager = new LinearLayoutManager(productActivity.this);

                    rvProd.setLayoutManager(layoutManager);
                    prodAdapater prodAdapater = new prodAdapater(getApplicationContext(), productList);
                    rvProd.setAdapter(prodAdapater);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(productActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            Intent intent = new Intent(productActivity.this, productActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.cart:
                            Intent intent2 = new Intent(productActivity.this, CartActivity.class);
                            startActivity(intent2);
                            break;
                        case R.id.editprofile:
                            Intent intent3 = new Intent(productActivity.this, EditProfile.class);
                            startActivity(intent3);
                            break;
                        case R.id.nearby:
//                                openFragment(NotificationFragment.newInstance("", ""));
                            return true;
                        case R.id.logout:
                            logout();
                            break;
                    }
                    return false;
                }
            };

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
