package com.example.mobilemanagementsystem.Adapater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemanagementsystem.Activity.OrderActivity;
import com.example.mobilemanagementsystem.Model.Product;
import com.example.mobilemanagementsystem.R;
import com.example.mobilemanagementsystem.URL.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class prodAdapater extends RecyclerView.Adapter<prodAdapater.prodViewholder> {

    Context mcontext;
    List<Product> productList;


    public prodAdapater(Context mcontext, List<Product> productList)
    {
        this.mcontext=mcontext;
        this.productList=productList;
    }


    @NonNull
    @Override
    public prodAdapater.prodViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.activity_productdetail,parent,false);
        return new prodAdapater.prodViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final prodAdapater.prodViewholder holder, int position) {
        final Product prod = productList.get(position);
        holder.prodName.setText(prod.getProductName());
        holder.prodBrand.setText(prod.getBrand());
        holder.prodPrice.setText(prod.getPrice());

        final String imgPath = Url.imagePath+prod.getImage();
        System.out.println("The image path is :" + imgPath);
        Picasso.get().load(imgPath).into(holder.prodImg);


        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext.getApplicationContext(), OrderActivity.class);
                i.putExtra("prodName", prod.getProductName());
                i.putExtra("prodBrand", prod.getBrand());
                i.putExtra("prodPrice", prod.getPrice());
                i.putExtra("prodImage", imgPath);
                i.putExtra("prodId", prod.get_id());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class prodViewholder extends RecyclerView.ViewHolder {

        ImageView prodImg;
        TextView prodName, prodBrand, prodPrice;
        Button btnAddToCart;

        public prodViewholder(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.prodName);
            prodBrand = itemView.findViewById(R.id.prodBrand);
            prodPrice = itemView.findViewById(R.id.prodPrice);
            prodImg = itemView.findViewById(R.id.productImg);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }

}
