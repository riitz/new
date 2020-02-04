package com.example.mobilemanagementsystem.Model;

public class order {
    private String productId, quantity;

    public String getProductId() {
        return productId;
    }

    public order(String productId, String quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
