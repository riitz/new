package com.example.mobilemanagementsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemanagementsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



   private EditText etsearch;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        //getSupportActionBar().hide();
        //getSupportActionBar().hide();
        //etsearch = findViewById(R.id.etsearch);

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            Intent intent = new Intent(MainActivity.this, productActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.cart:
                            Intent intent2 = new Intent(MainActivity.this, CartActivity.class);
                            startActivity(intent2);
                            break;
                        case R.id.editprofile:
//                                openFragment(NotificationFragment.newInstance("", ""));
                            return true;
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
