package com.example.datasentapi.ModalClass;

public class ModalAddCart {
     String name;
     String price;
     String image;

    public ModalAddCart()
    {

    }
    public ModalAddCart(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharSequence getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
