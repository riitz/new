package com.example.mobilemanagementsystem.bll;

import com.example.mobilemanagementsystem.Interface.orderApi;
import com.example.mobilemanagementsystem.Model.order;
import com.example.mobilemanagementsystem.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class OrderBLL {

    boolean isSuccess = true;

    public boolean order( String id,String quantity) {
        order orders  = new order(id,quantity);
        orderApi orderApis = Url.getInstance().create(orderApi.class);
        Call<order> usersCall = orderApis.orderItems(Url.token, orders);

        try {
            Response<order> orderResponse = usersCall.execute();
            if (orderResponse.isSuccessful()){

                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
