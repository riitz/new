package com.example.mobilemanagementsystem.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemanagementsystem.R;

public class CartActivity extends AppCompatActivity {

           Toolbar toolbar;
           RecyclerView recyclerView;
           TextView txtprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.cart_toolbar);
        recyclerView = findViewById(R.id.recycler_cart);
        txtprice = findViewById(R.id.final_price);
    }
}
