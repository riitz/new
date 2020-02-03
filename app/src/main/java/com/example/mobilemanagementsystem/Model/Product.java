package com.example.mobilemanagementsystem.Model;

public class Product {
    private String _id;
    private String productName;
    private String brand;
    private String price;
    private String Image;

    public Product(String productName, String brand, String price, String Image) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.Image = Image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
}

