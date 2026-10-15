package com.oliva.pos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Prodotto {
    @Id   //primary key
    private String barCode;

    private String productName;
    private double price;

    //Empty constructor for JPA
    public Prodotto(){}

    public Prodotto(String productName, String barCode, double price){
        this.productName = productName;
        this.barCode = barCode;
        this.price = price;
    }

    public String getBarCode(){
        return this.barCode;
    }

    public String getProductName(){
        return this.productName;
    }

    public double getPrice(){
        return this.price;
    }

}
