package com.example.mobilemanagementsystem.Adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemanagementsystem.Model.order;
import com.example.mobilemanagementsystem.R;
import com.example.mobilemanagementsystem.URL.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.prodViewholder> {


    Context mcontext;
    List<order> orderList;


    public cartAdapter(Context mcontext, List<order> orderList)
    {
        this.mcontext=mcontext;
        this.orderList=orderList;
    }


    @NonNull
    @Override
    public cartAdapter.prodViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.cart,parent,false);
        return new cartAdapter.prodViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final cartAdapter.prodViewholder holder, int position) {
        final order orders = orderList.get(position);
        holder.name.setText(orders.product.getProduct());
        holder.brand.setText(orders.product.getBrand());
        holder.price.setText(orders.product.getPrice());
        holder.quantity.setText(orders.getQuantity());

        final String imgPath = Url.imagePath+orders.product.getImage();
        System.out.println("The image path is :" + imgPath);
        Picasso.get().load(imgPath).into(holder.image);



    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class prodViewholder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, brand, quantity, price;


        public prodViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvProduct);
            brand = itemView.findViewById(R.id.tvBrandname);
            quantity = itemView.findViewById(R.id.tvQuantityprodut);
           image = itemView.findViewById(R.id.tvImage);
           price = itemView.findViewById(R.id.tvPriceName);

        }
    }

}
